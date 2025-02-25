/***************************************************
Filename: Syscall.c
Author: MIDN Ian Coffey (m261194)
Demo C Code
***************************************************/

// Import Libraries
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char * argv[])
{
    char goodbye[] = "Hello\n";

    syscall(1, 1, goodbye, 10);
}
