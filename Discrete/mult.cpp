/***************************************************
Filename: mult.cpp
Author: MIDN Ian Coffey (m261194)
Determine if A Multiplicative Inverse Exist
***************************************************/

// Import Libraries
#include <iostream>
using namespace std;

int main() 
{
    // Variable Declarations
    int n, multInverse = false;

    // Prompt User Input
    cin >> n;

    cout << n << ": ";

    for (int i = 0; i < n; i++) 
    {
        for (int j  = 0; j < n; j++) 
        {
            if (((j * i) % n) == 1) 
            {
                bool multInverse = true;
                break;
            }
        }

        if (multInverse) {
            cout << i << " ";
        }        
    }
    cout << endl;
    return 0;
}
