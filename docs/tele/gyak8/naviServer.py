import socket
import struct
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_address = (sys.argv[1], int(sys.argv[2]))
sock.bind(server_address)
sock.settimeout(1.0)
unpacker = struct.Struct('I I I I 6s')
answerPack = struct.Struct('I')
connection_mb = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mb_address = (sys.argv[3],int(sys.argv[4]))
connection_mb.connect(mb_address)
running = True
while(running):
	try:
		try:
			data, address = sock.recvfrom(unpacker.size)
			unpacked_data = unpacker.unpack(data)
			print("Jött egy kérés:")
			print("x:",unpacked_data[0])
			print("y:",unpacked_data[1])
			print("idő:" + str(unpacked_data[2]) + ":" + str(unpacked_data[3]))
			print("neptun kód:",unpacked_data[4].decode())
			print("Továbbítottam a mapbank szervernek")
			connection_mb.sendall(data)
			mbAnswer = connection_mb.recv(answerPack.size)
			sock.sendto(mbAnswer,address)
			print("Visszaadtam a kliensnek a mapbank válaszát")
		except socket.timeout:
			pass
	except KeyboardInterrupt:
		running = False

print("Quitting..")
sock.close()
