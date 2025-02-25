#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char * argv[])
{
  // Variable Declaration
  int ccnum[16];

  // Check if Argument is Given
  if (argc == 1)
  {
    fprintf(stderr, "ERROR: require credit card number\n");
    exit(1);
  }

  // Check Length of Number
  int len = strlen(argv[1]);
  if (len != 16)
  {
    fprintf(stderr, "ERROR: Invalid credit card number: Bad Length\n");
    exit(1);
  }

  // Check for Invalid Input
  for (int i = 0; i < len; i++)
  {
    if (!(argv[1][i] >= 48) || !(argv[1][i] <= 57))
    {
      fprintf(stderr, "ERROR: Invalid credit card number: Bad number '%c'\n", argv[1][i]);
      exit(1);
    }
  }

  // Copy All Numbers to CCnum Array
  for (int i = 0; i < 16; i++)
  {
    ccnum[i] = argv[1][i] - 48;
  }

  // Double Every Other Digit
  for (int i = 0; i < 16; i += 2) 
  {
    ccnum[i] *= 2;
  }

  // Replace Every Digit With Sum of Digits if >9
  for (int i = 0; i < 16; i += 2)
  {
    if (ccnum[i] > 9) 
    {
      char num[2];
      sprintf(num, "%d", ccnum[i]);
      int digit1 = num[0] - 48;
      int digit2 = num[1] - 48;
      ccnum[i] = digit1 + digit2;
    }
  }

  // Sum Digits
  int total = 0;
  for (int i = 0; i < 16; i++) 
  {
    total += ccnum[i];
  }

  // Check if Number is valid
  if (total % 10 == 0) 
  {
    printf("VALID");
    exit(0);
  } else {
    printf("INVALID");
    exit(2);
  }



  // OUTPUT strinsg for you to use to pass automated tests
  //  --  printf("VALID\n");
  //  -- printf("INVALID\n");

  // RETURN values to use
  //  -- return 0 : valid credit card number
  //  -- return 1 : error occured
  //  -- return 2 : invalid credit card number


}