/***************************************************
Filename: Netowrks.c
Author: MIDN Ian Coffey (m261194)
Demo C Code
***************************************************/

// Import Libraries
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main(int argc, char * argv[])
{
    int sock;
    struct sockaddr_in saddr_in;

    //fill in the address for usna.edu
    saddr_in.sin_family = AF_INET;
    inet_aton("10.4.32.41", &(saddr_in.sin_addr));
    saddr_in.sin_port = htons(80); // set port connect to 80

    //open a socket
    if( (sock = socket(AF_INET, SOCK_STREAM, 0))  < 0){
    perror("socket");
    exit(1);
    }

    //connect socket to the server
    if(connect(sock, (struct sockaddr *) &saddr_in, sizeof(struct sockaddr_in)) < 0){
        perror("connect");
        exit(1);
    }


    // NOW WE ARE CONNECTED TO THE SERVER 
    /* read.c */

    //read from socket and write to stdout
    int n;
    char buf[] = {"Hello World"};
    char BUF_SIZE[28];

    while( (n = read(sock, buf, BUF_SIZE)) > 0){
        if( write(1, buf, n) < 0){
            perror("write");
            exit(1);
        }
    }

    if( n < 0 ){
        perror("read");
        exit(1);
    }

}