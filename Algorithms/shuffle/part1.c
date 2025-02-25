// Filename: part1.c
// Author: MIDN 2/C Ian Coffey (m261194)
// Perform a selection sort to sort midshipmen 

// External resources used...
/*** 
https://www.geeksforgeeks.org/c-program-to-read-contents-of-whole-file/
https://www.tutorialspoint.com/c_standard_library/c_function_fscanf.htm
https://www.tutorialspoint.com/c_standard_library/c_function_realloc.htm
***/

// Import libraries
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Midshipmen struct
typedef struct Mid {
    char name[40];
    int company;
} Mid;

// Function to perform a selection sort on the mid array
void selectionSort(Mid array[], int size) {

    for (int i = 0; i < size - 1; i++) {
        int m = i;
        for (int j = i + 1; j < size; j++) {
            if (array[j].company < array[m].company) {
                m = j;
            }
        }

        // Swap elements
        if (m != i) {
            // Print names that need to be swapped
            printf("%s %s\n", array[m].name, array[i].name);

            Mid tmp = array[m]; 
            array[m] = array[i];
            array[i] = tmp;
        }
    }
}

// Main
int main(int argc, char **argv) {
    
    // Read in midshipmen from file
    int capacity = 1;
    int numMids = 0;
    Mid *midArray = (Mid *) malloc(capacity * sizeof(Mid));
    
    // Read midshipmen from stdin using scanf
    while (scanf("%d %s\n", &midArray[numMids].company, midArray[numMids].name) == 2) { 
        // Increase count
        numMids++;

        // Double capacity if needed
        if (numMids == capacity) {
            capacity *= 2;
            midArray = (Mid *) realloc(midArray, capacity * sizeof(Mid));
        }
    }
    
    // Perform selection sort on mids
    selectionSort(midArray, numMids);

    return 0;
}
