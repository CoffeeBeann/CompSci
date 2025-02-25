#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int check1(char * str);
int check2(char * str1);

int check1(char *str)
{
  int length = strlen(str);

  // Iterate Forwards
  char forward[length];
  for (int i = 0; i < length; i++)
  {
    forward[i] = str[i];
  }

  // Iterate Backwards
  char backwards[length];
  int k = 0;
  for (int j = length - 1; j >= 0; j--)
  {
    backwards[k] = str[j];
    k++;
  }

  // Add Null Terminators
  backwards[length] = '\0';
  forward[length] = '\0';

  // Compare Strings
  return (strcmp(backwards,forward) == 0);
}

int check2(char *str1)
{
  // Copy String
  char str2[1024];
  strcpy(str2, str1);

  // Reverse String
  int len = strlen(str1);
  char reverse[len];
  int j = 0;
  for (int i = len - 1; i >= 0; i--)
  {
    reverse[j] = str2[i];
    j++;
  }

  // Add Null Terminators
  reverse[len] = '\0';

  return (strcmp(str1, reverse) == 0);

}

int main(int argc, char * argv[]){

  char str[1024];

  printf("Enter a string:\n");

  scanf("%1023s",str);

  if(check1(str)){
    printf("Palindrome according to check 1\n");
  }else{
    printf("NOT a palindrome according to check 1\n");
  }


  if(check2(str)){
    printf("Palindrome according to check 2\n");
  }else{
    printf("NOT a palindrome according to check 2\n");
  }

}