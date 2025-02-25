# Filename: client.py
# Author: MIDN 2/C Ian Coffey
# Construct a TCP client to handle requests

# Import libraries
import socket
import os

# File to send (update with the actual path to your image)
file_path = 'image.jpg' 

# Create a TCP/IP socket
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the server's address and port
clientSocket.connect(('localhost', 12000))

# Get the file size for the Content-Length header
file_size = os.path.getsize(file_path)

# Open the file in binary mode
with open(file_path, 'rb') as f:
    file_data = f.read()

# Create the HTTP POST request headers
request = f"POST /{os.path.basename(file_path)} HTTP/1.1\r\n"
request += f"Host: localhost\r\n"
request += f"Content-Length: {file_size}\r\n"
request += "Connection: close\r\n\r\n"

# Send the HTTP headers
clientSocket.send(request.encode())

# Send the file data
clientSocket.send(file_data)

# Receive and print the server's response
response = clientSocket.recv(1024)
print(response.decode())

# Close the connection
clientSocket.close()
