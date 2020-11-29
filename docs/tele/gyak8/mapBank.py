import select
import socket
import sys
import struct
import random

class BankServer:
	def __init__(self, addr='localhost', port=10001, timeout=1):
		self.coordinates = {}
		self.server = self.setupServer(addr, port)
		self.inputs = [ self.server ]
		self.outputs = []
		self.timeout=timeout
		self.handleConnections()

	def setupServer(self, addr, port):
		server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		server.setblocking(0)
		server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
		server_address = (addr, port)
		server.bind(server_address)
		server.listen(5)
		return server

	def handleNewConnection(self, sock):
		connection, client_address = sock.accept()
		connection.setblocking(0)
		self.inputs.append(connection)
		self.outputs.append(connection)

	def handleSocketError(self, err, sock, writable):
		if sock in self.outputs:
			self.outputs.remove(sock)
			self.inputs.remove(sock)
		writable.remove(sock)

	def handleDataFromClient(self, sock, writable):
		unpacker = struct.Struct('I I I I 6s')
		answerPack = struct.Struct('I')
		try:
			data = sock.recv(unpacker.size)
		except socket.error as err:
			self.handleSocketError(err, sock, writable)
		else:
			if data:
				unpackedData = unpacker.unpack(data)
				print("Jött egy kérés:")
				print("x:",unpackedData[0])
				print("y:",unpackedData[1])
				print("idő:" + str(unpackedData[2]) + ":" + str(unpackedData[3]))
				print("neptun kód:",unpackedData[4].decode())
				coord = (unpackedData[0],unpackedData[1])
				answer = 0
				if coord in self.coordinates:
					answer = self.coordinates[coord]
					print("A válasz megvan tárolva:",self.coordinates[coord])
				else:
					answer = random.randrange(30,301)
					print("Random generáltam és elmentettem a választ:",answer)
					self.coordinates[coord] = answer
				packedData = answerPack.pack(answer)
				sock.sendall(packedData)
			else:
				if sock in self.outputs:
					self.outputs.remove(sock)
				self.inputs.remove(sock)
				writable.remove(sock)
				sock.close()

	def handleInputs(self, readable, writable):
		for sock in readable:
			if sock is self.server:
				self.handleNewConnection(sock)
			else:
				self.handleDataFromClient(sock, writable)

	def handleExceptionalCondition(self, exceptional):
		for sock in exceptional:
			self.inputs.remove(sock)
			if sock in self.outputs:
				self.outputs.remove(sock)
			sock.close()
	
	def handleConnections(self):
		while self.inputs:
			try:
				readable, writable, exceptional = select.select(self.inputs, self.outputs, self.inputs, self.timeout)
	
				if not (readable or writable or exceptional):
					continue
	
				self.handleInputs(readable, writable)
				self.handleExceptionalCondition(exceptional)
			except KeyboardInterrupt:
				for c in self.inputs:
					c.close()
				self.inputs = []
		
MapServer = BankServer(sys.argv[1],int(sys.argv[2]),1)