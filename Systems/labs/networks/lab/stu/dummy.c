#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

int main(int argc, char *argv[]){

	struct addrinfo *result6;		// to store results
	struct addrinfo *cur_result6;    // to store results
	struct addrinfo hints6;			// to indicate information we want
	struct sockaddr_in6 *saddr6;		// to reference address (IPv4)
	int s6; 							// for error checking

	// Filter for IPv6
	memset(&hints6,0,sizeof(struct addrinfo));  //zero out hints
 	hints6.ai_family = AF_INET6; //we only want IPv6 addresses

	if ((s6 = getaddrinfo(argv[1], NULL, &hints6, &result6)) != 0)
	{
		fprintf(stderr, "getaddrinfo: %s\n",gai_strerror(s6));
		exit(1);
	}

    char str[INET_ADDRSTRLEN];

	for(cur_result6 = result6; cur_result6 != NULL; cur_result6 = cur_result6->ai_next)
	{
		//convert generic socket address to inet socket address
  		if (cur_result6->ai_protocol == IPPROTO_TCP && cur_result6->ai_family == AF_INET6)
		{
			saddr6 = (struct sockaddr_in6 *) cur_result6->ai_addr;
			printf("%s has IPv6 address %s\n", argv[1], inet_ntop(AF_INET6, &(saddr6->sin6_addr), str,INET6_ADDRSTRLEN));
		}
 	}

	freeaddrinfo(result6);
	
}
