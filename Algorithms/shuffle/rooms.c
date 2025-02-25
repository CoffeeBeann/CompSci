// Filename: rooms.c
// Author: MIDN 2/C Ian Coffey (m261194)
// Sort midshipmen rooms in as litle swaps as possible 

// Import libraries
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Midshipmen struct
typedef struct Mid {
    char name[40];
    int company;
} Mid;

// Function to perform a target sort on the mid array
void targetSort(Mid array[], int totalMids) {

    int placeHolder = 0;
    int offset = 0;
    for (int i = 0; i < 100; i++) {
        int target = array[placeHolder].company;
        placeHolder = 0;
        placeHolder += offset;

        for (int j = placeHolder + 1; j < totalMids; j++) {
            
            // Put placeHolder in proper position in array
            while (array[placeHolder].company == target) {
                placeHolder++;
                j++;
                offset++;
            }

            if (j < totalMids && array[j].company == target) { 

                // Swap elements
                printf("%s %s\n", array[placeHolder].name, array[j].name);
                Mid tmp = array[placeHolder]; 
                array[placeHolder] = array[j];
                array[j] = tmp; 

                // Increment placeholder
                placeHolder++;
                offset++;
            }
        }
    }
}

// Main
int main(int argc, char **argv) {
    
    // Read in midshipmen from file
    int capacity = 1;
    int totalMids = 0;
    Mid *midArray = (Mid *) malloc(capacity * sizeof(Mid));
    
    //int *companyNum = calloc(100, sizeof(int));

    // Read midshipmen from stdin using scanf
    while (scanf("%d %s\n", &midArray[totalMids].company, midArray[totalMids].name) == 2) { 
        
        // Increase count
        totalMids++;
        

        //companyNum[midArray[totalMids].company]++;

        // Double capacity if needed
        if (totalMids == capacity) {
            capacity *= 2;
            midArray = (Mid *) realloc(midArray, capacity * sizeof(Mid));
        }

    }

    targetSort(midArray, totalMids);
    
    return 0;
}
