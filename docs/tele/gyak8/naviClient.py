#Példa hívás a 3-ra:
#mapBank.py localhost 10005
#naviServer.py localhost 10004 localhost 10005
#naviClient.py localhost 10004 20 10 12:11
#

from socket import socket, AF_INET, SOCK_DGRAM
import struct
import sys

client = socket(AF_INET, SOCK_DGRAM)
server_addr = (sys.argv[1],int(sys.argv[2]))

packer = struct.Struct('I I I I 6s')
unpacker = struct.Struct('I')

x = int(sys.argv[3])
y = int(sys.argv[4])
hour = int(sys.argv[5].split(':')[0])
minute = int(sys.argv[5].split(':')[1])
neptun = "KNDQJI"

values = (x,y,hour,minute,neptun.encode())
packed_data = packer.pack(*values)

client.sendto(packed_data, server_addr)
print("elküldtem")
data, _ = client.recvfrom(unpacker.size)
unpacked = unpacker.unpack(data)
print("A távolság amit menni kell:",unpacked[0])

client.close()
