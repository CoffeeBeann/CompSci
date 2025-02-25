/***************************************************
Filename: Pipes.c
Author: MIDN Ian Coffey (m261194)
Demo C Code
***************************************************/

// Import Libraries
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char * argv[]){

    char hello[] = "Hello World!\n";
    char c;
    int pfd[2]; 
    pid_t cpid;
    int status;

    if ( pipe(pfd) < 0){  // open pipe
        perror("pipe");
        return 1;
    }

    cpid = fork();

    if( cpid == 0 ){  /* CHILD */

        close(pfd[1]);  // close write end 
        while (read(pfd[0], &c, 1) > 0) { write(1, &c,1); }
        close(pfd[0]);  // close read end
        _exit(0);

    }else if ( cpid > 0){  /* PARENT */

        close(pfd[0]);  // close read end
        write(pfd[1], hello, strlen(hello));
        close(pfd[1]);  // close write end
        wait(&status);

    }else{
        perror("fork");
        return 1;
    }
    return 0;
}
