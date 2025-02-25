/***************************************************
Filename: Threads.c
Author: MIDN Ian Coffey (m261194)
Demo C Code
***************************************************/

// Import Libraries
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int i = 0;
void * hello_fun(void * args) // Void * is for when we want to return anything 
{
    printf("Hi %d\n", i);
    i++;
    return NULL;
}

int main(int argc, char * argv[])
{
    pthread_t thread[3];

    // Make New Thread
    for (int j = 0; j < 3; j++) {
        pthread_create(&thread[j], NULL, hello_fun, NULL);
    }

    // Wait Until Thread is Done
    pthread_join(thread[2], NULL);
}
