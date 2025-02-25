#!usr/bin/python3

from socket import *
import struct
raw_socket = socket(AF_INET, SOCK_RAW, IPPROTO_RAW)
udp_payload = b"Hello World\n" 

# 8 + UDP len to account for header
udp_header = struct.pack(">4H",12345, 9000, 8 + len(udp_payload), 0)
ip_payload = udp_header + udp_payload



# IP vars
version = 4
ihl = 5 
version_ihl = (version << 4) | ihl # put version and ihl in one byte
type_of_service = 0
total_length = 20 + len(ip_payload)
ip_header = struct.pack(">BBH", version_ihl, type_of_service, total_length)

# Pack more stuff 
ip_header += struct.pack(">HH", 12345, 0)
ttl = 64
protocol = 17
checksum = 0
ip_header += struct.pack(">BBH", ttl, protocol, checksum)

# Pack source and destination address
ip_header += struct.pack(">4B", 192,168,42,8) # src
ip_header += struct.pack(">4B", 10,0,2,15) # dest (target)
ip_pkt = ip_header + ip_payload
addr = ("10.0.2.15", 9000)


# Send packet
addr = ("10.0.2.15", 9000)
raw_socket.sendto(ip_pkt, addr)
