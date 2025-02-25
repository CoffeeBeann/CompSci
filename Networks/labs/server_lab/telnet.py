# Filename: TCPserver.py
# Author: MIDN 2/C Ian Coffey (m261194)
# Make a TCP Server

# Import Libraries
from socket import *

# Create Port & Socket
serverPort = 12346
serverSocket = socket(AF_INET, SOCK_STREAM)

# Bind & Listen
serverSocket.bind(('',serverPort)) 
serverSocket.listen(1)

# Listen for Messages 
print ('The Server is Ready...') 
while True: 
    connectionSocket, addr = serverSocket.accept() 
    sentence = connectionSocket.recv(1024).decode()  
    connectionSocket.send(sentence.encode())
    connectionSocket.close()
