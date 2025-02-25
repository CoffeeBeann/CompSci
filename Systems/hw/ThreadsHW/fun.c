#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int shared = 0;

void * fun(void * args){
  int i;
  for(i=0;i<1000000;i++){
    shared++;
  }
  return NULL;
}

int main(){
  pthread_t t1,t2;

  pthread_create(&t1, NULL, fun, NULL);
  pthread_create(&t2, NULL, fun, NULL);

  pthread_join(t1, NULL);
  pthread_join(t2, NULL);

  printf("shared: %d\n", shared);

}
