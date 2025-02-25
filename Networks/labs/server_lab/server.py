# Filename: server.py
# Author: MIDN 2/C Ian Coffey ***Collarborated with 2/C Lewis***
# Construct a TCP server to handle request

# Import libraries
from socket import *

# Set up server
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)
print("Server is ready to receive...")

# Begin listening for messages
while True:

    try:
        # Accept Messages
        connectionSocket, addr = serverSocket.accept()

        # Save message from client
        message = connectionSocket.recv(1024).decode()
    
        # Open file and read data
        filename = message.split()[1]
        contentType = ""

        # Check for html
        if (filename.endswith('.html')):
            contentType = "text/html"
            f = open(filename[1:])
            outputdata = f.read()
           
            # Construct response
            returnMessage = "HTTP/1.1 200 OK\r\n"
            returnMessage += f"Content-Type: "
            returnMessage += contentType
            returnMessage += "\r\n\r\n"

            connectionSocket.send(returnMessage.encode())
            for i in range(0, len(outputdata)):
                connectionSocket.send(outputdata[i].encode())
        
        elif (filename.endswith('.jpeg')):
            contentType = "image/jpeg"
            with open(filename[1:],'rb') as f:
                outputdata = f.read()

            # Construct response
            returnMessage = "HTTP/1.1 200 OK\r\n"
            returnMessage += f"Content-Type: "
            returnMessage += contentType
            returnMessage += "\r\n\r\n"

            # Encode response and return to client
            connectionSocket.send(returnMessage.encode())
            connectionSocket.send(outputdata)
    
        # Close socket
        connectionSocket.close()
    except Exception as e:
        print(e)
        
