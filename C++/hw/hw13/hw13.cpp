/***************************************************
Filename: hw.cpp
Author: MIDN Ian Coffey
Output Matrix Table Given Inputed Numbers
***************************************************/

// Import Libraries
#include <iostream>
#include <fstream>

using namespace std;

int main() 
{
    // Variable Declaration
    int width, height, sideAsterisk, offset;
    ifstream inputFile("input.txt");

    inputFile >> height >> width;

    for (int i = 0; i < height; i++) 
    {
        // For Loop To Output Width Asterisk
        for (int k = 0; k < width - 1; k++) 
        {
            // Determine If @ Begining or End of Square
            if (i == 0 || i == height - 1) 
            {
                cout << "*";

            } else if (k == width - 2){

                cout << "*";

            } else {

                cout << " ";
            }
        }

        // Output New Line
        cout << endl;
    }

    // End Program
    inputFile.close();
    return 0;
    
}   
