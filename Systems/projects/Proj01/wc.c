/*******************************************************
Filename: wc.c
Author: MIDN Ian Coffey (m261194)
To Count the # of Words, Lines, and Characters of a File
********************************************************/

// Import Libraries
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

// Function to Return a Int * Containging Line, Word, & Char Count
// returnList = {line, word, char}
int * getNumbers(FILE * f) 
{
    // Variable & Object Declaration
    int * returnList = calloc(3, sizeof(int));
    bool charBefore = false;

    while(!feof(f)) 
    {
        // Get Next Character
        char next = fgetc(f);

        // Increment Character Count
        returnList[2]++;

        // Check for New Line
        if (next == '\n') 
        {
            returnList[0]++;

            // Check for Word
            if (charBefore) 
            {
                returnList[1]++;
                charBefore = false;
            }
        }

        // Check for Character 
        else if (next >= 33 && next <= 126) 
        {
            charBefore = true;
        }

        // Check For Word
        else if (isspace(next) && charBefore) 
        {
            returnList[1]++;
            charBefore = false;
        }
        
    }

    // Decrement Char Count to Account for 1 Extra Iteration
    returnList[2]--;

    // Return Arrays
    return returnList;

}

int main(int argc, char * argv[]) 
{
    // Variable Declarations
    int * numList = calloc(3, sizeof(int));
    int totalLine = 0, totalWord = 0, totalChar = 0;
    int optionNum = 0, fileNum = 0;

    // Boolean Option Delcarations
    bool readLine = false;
    bool readWord = false;
    bool readChar = false;
    bool outputNumbers = false;
    
    // Argument & Error Detection
    for (int i = 0; i < argc; i++) 
    {
        // Check for '-' option
        if (argv[i][0] == '-') 
        {
            if (argv[i][1] == 'w') 
            {
                readWord = true;
                optionNum++;

            } else if (argv[i][1] == 'c') 
            {
                readChar = true;
                optionNum++;

            } else if (argv[i][1] == 'l') 
            {
                readLine = true;
                optionNum++;

            } else 
            {
                fprintf(stderr, "ERROR: unknown option '-%c'\n", argv[i][1]);
                exit(1);
            }

        } else if (argv[i][0] == '+' || argc == 1) 
        { 
            // Check for '+' Option
            fileNum++;

            numList = getNumbers(stdin);

            // Output Numbers
            printf("-stdin- ");
            outputNumbers = true;

            // Add to Totals
            totalChar += numList[2];
            totalWord += numList[1];
            totalLine += numList[0];       
            

        } else if (argv[i][0] >= 65 && argv[i][0] <= 122) 
        {
            // Assume Text File
            FILE * inFile = fopen(argv[i], "r");

            // Check if File Exists
            if (!inFile) 
            {
                fprintf(stderr, "ERROR: file %s cannot be opened\n", argv[i]);

            } else 
            {
                // Increment File Num
                fileNum++;
        
                // Get Numbers
                numList = getNumbers(inFile);

                // Add to Totals
                totalChar += numList[2];
                totalWord += numList[1];
                totalLine += numList[0];       

                // If No Option Preferences were Entered, Output All Numbers
                if (optionNum == 0) 
                {
                    readLine = true;
                    readWord = true;
                    readChar = true;
                }

                // Close File
                fclose(inFile);

                // Ensure Output Num Boolean is On
                printf("%s ", argv[i]);
                outputNumbers = true;
            }
        }

        // Check for STDIN Solo Case
        if (argc == 1) 
        {
            readLine = true;
            readWord = true;
            readChar = true;
        }

        // Output Nubmers After Parsing
        if (outputNumbers) 
        {
            if (readLine) 
            {
                printf("%d ", numList[0]);
            }
            if (readWord) 
            {
                printf("%d ", numList[1]);
            }
            if (readChar) 
            {
                printf("%d", numList[2]);
            }

            // New Line
            printf("\n");

            // Reset Boolean
            outputNumbers = false;
        }
    }

    // Output Totals If Need Be
    if (fileNum > 1) 
    {
        printf("total ");

        if (readLine) 
        {
            printf("%d ", totalLine);
        }
        if (readWord) 
        {
            printf("%d ", totalWord);
        }
        if (readChar) 
        {
            printf("%d", totalChar);
        }

        // New Line
        printf("\n");
    }

    // Free Return List
    free(numList);

    // Exit Program
    exit(0);

}
