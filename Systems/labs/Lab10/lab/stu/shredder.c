/* IC221 Lab: Signals
 * Program to time a process using alarms and kill if it runs more than 5 seconds.
 * MIDN Ian Coffey
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/signal.h>
#include <sys/wait.h>
#include <sys/types.h>

int ticks = 0;
pid_t cpid;
pid_t ppid;

void tick(int signum) 
{
	ticks++;
	if (ticks > 5) {
		kill(cpid, 9);
	} else {
	
		printf("%d: tick-tock\n", ticks);
		alarm(1);
	}
}

int main(int argc, char * argv[]) 
{
	int status;

	if ((cpid = fork()) == 0) {
		/*CHILD*/
		execvp(argv[1], argv+1);
		perror(argv[1]);
		_exit(1);

	} else if (cpid > 0) {
		
		// Register Alarm Handler
		signal(SIGALRM, tick);

		//TODO: Complete the program
		alarm(1);
		ppid = getpid();
		ppid = wait(&status);
		
		// If the child finished before 3 ticks:
		if (ticks < 3)
			printf("Blast that grotesque ganglion! You let them get away!\n");
		//
		// If the child finished after 3 ticks but not killed:
		else if (ticks >= 3 && ticks < 5)
			printf("Sayonara you shell-backed simpletons. I'll get you next time!\n");
		//
		// If the child was killed:
		else if (ticks >= 5)
			printf("Tonight I dine on turtle soup! Muhaha!\n");
		//
		// (hint: don't forget to turn off unscheduled alarms)
		alarm(0);

		// (hint: recall the WIF---() functions ...)

	} else {
		/*ERROR*/
		perror("fork");
		_exit(1);
	}

	return 0;
}
