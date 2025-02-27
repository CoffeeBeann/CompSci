// Filename: sockets.c
// Author: MIDN 2/C Ian Coffey
// Demo sockets

// Import libraries
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main(int argc, char **argv) {
    
  int sock = socket(2,0,0);

  const struct sockaddr *addr 
  int s = bind(sock,
  struct sockaddr_in saddr_in;
  char buff[256];
    
  saddr_in.sin_family = AF_INET;
  inet_aton("0.0.0.0", &(saddr_in.sin_addr));

    

}

