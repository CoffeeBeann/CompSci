#include <stdio.h>
#include <stdlib.h>

//TODO PART 2: complete the print_array function to have the right output
//prints out the array in the desired format, for exmaple:
// { 0 1 2 3 4 }
void print_array(int a[], int length)
{
  printf("{ ");

  for (int i = 0;  i < length; i++) 
  {
    printf("%d ", a[i]);
  }

  printf("} \n");
}

//TODO PART 3: compete the reverse_array function
// hint: you'll need a tmp variable for swaps
void reverse_array(int a[], int length)
{
  int tmp;
  int start = 0;
  int end = length - 1;

  while (end > start) 
  {
    tmp = a[start];
    a[start] = a[end];
    a[end] = tmp;

    start++;
    end--;
  }
  
}

//TODO PART 4: complete the randomize array function
// hint: random() returns a random number, but you want it to be in
//       the range of the length of the array. Try using modulo
// hint: try randomnly swapping all index with some other index
void randomize_array(int a[], int length)
{
  int randIndex;
  int tmp;

  for (int i = 0; i < length; i++) 
  {
    randIndex = random() % length;

    tmp = a[i];
    a[i] = a[randIndex];
    a[randIndex] = tmp;

  }

}

//TODO PART 5: complete the sort_arry function (smallest to biggest)
// hint: you'll probably need two for loops 
// hint: Check out insertion sort on wikipedia (it's ok to use that source)
void sort_array(int a[],int length)
{
  int i = 0;
  int tmp;

  // While Loop to traverse array and swap values
  while (i != length - 1) 
  {
    if (a[i] > a[i+1]) 
    {
      tmp = a[i];
      a[i] = a[i+1];
      a[i+1] = tmp;
      i = 0;
    } else {
      i++;
    }
  }
}

int main(int argc, char *argv[]){

  int len=0;    //store the length of the array, initially 0 but will change
  int res;      //store the result of scanf() operations
  int choice;   //store the user choice for operations
  int i;        //an interator for loops

  srandom(1992);//seed random number generator

  printf("Enter the length:\n");

  //TODO PART 1: Requst how many numbers
  // hint: don't forget to return 1 on error
  if (scanf("%d", &len) == 0) 
  {
    printf("ERROR: Invalid input\n");
    return 1;

  }

  int array[len]; //declare array now that we know its size

  printf("Enter %d numbers (space separated):\n", len);

  //TODO PART 1: Read in desired numbers
  // hint: don't forget to return 1 on error
  for (i = 0; i < len; i++) 
  {
    if (scanf("%d", &array[i]) == 0) 
    {
      printf("ERROR: Invalid input\n");
      return 1;
    }
  }
 

  // while loop for operations, run until exit or EOF
  while(1){
    printf("Choose an operation:\n");
    printf("(0) : exit\n");
    printf("(1) : print array\n");
    printf("(2) : reverse array\n");
    printf("(3) : randomize array\n");
    printf("(4) : sort array\n");
    res = scanf("%d",&choice);

    // error check
    if(res == 0)
    {
      char c;
      printf("ERROR: Invalid input. Choose again.\n");

      scanf("%c",&c);
      continue;

    } else if (res == EOF) 
    {
      break;
    }

    if (choice == 0)
    {
      break;
    } else if (choice == 1) 
    {
      print_array(array, len);

    } else if (choice == 2) 
    {
      reverse_array(array, len);
      print_array(array, len);

    } else if (choice == 3) 
    {
      randomize_array(array, len);
      print_array(array, len);
      
    } else if (choice == 4) 
    {
      sort_array(array, len);
      print_array(array, len);
      
    } else{
      printf("ERROR: Invalid number. Choose again.\n\n");
      continue;
    }
    
  }

  return 0; //return 0 on success

}
