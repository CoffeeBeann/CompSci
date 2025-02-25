/***************************************************
Filename: Alarm.c
Author: MIDN Ian Coffey (m261194)
Demo C Code
***************************************************/

// Import Libraries
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/signal.h>
#include <signal.h>

void alarm_h(int sig) 
{
    printf("alarm going off\n");
}

int main()
{
   struct sigaction action;
   action.sa_handler = alarm_h;
   sigaction(SIGALRM, &action, NULL);
}
