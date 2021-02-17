import socket
import sys

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to the port
server_address = ('localhost', 10000)
#server_address = ('', 0)	#any interface, any port

print('starting up on %s port %s' % server_address)
sock.bind(server_address)

# Listen for incoming connections
sock.listen(1)
sock.settimeout(1.0)

while True:
    try:
        # Wait for a connection
        print('waiting for a connection')
        connection, client_address = sock.accept()

        print('connection from', client_address)

        # Receive the data in small chunks and retransmit it
        while True:
            data = connection.recv(16)
            print('received "%s"' % data.decode())
            if data:
                print('sending data back to the client')
                connection.sendall(data)
            else:
                print('no more data from', client_address)
                break
    except :
        pass
            
    finally:
        # Clean up the connection
        try:
            connection
        except NameError:
            pass
        else:
            connection.close()

