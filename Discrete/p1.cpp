/***************************************************
Filename: demo.cpp
Author: MIDN Ian Coffey (m261194)
demo lol
***************************************************/

// Import Libraries
#include <iostream>
#include <fstream>

using namespace std;

string var(string pre, int idx);

int main() 
{
    // Variable Declarations
    int n, w, num; 
    char discard;

    // Read In User Input
    cin >> discard >> discard >> n;
    
    for(int i = 1; i < n + 1; i++) 
    {
        cin >> num;
        cout << "(" << var("a",i-1) << " => " << var("w",i-1) << " = " << num << ") & ";
        cout << "(~" << var("a",i-1) << " => " << var("w",i-1) << " = " << i-1 << ") & " << endl;
        
    }	

    // End Program
    return 0;
}

string var(string pre, int idx) 
{
    return pre + to_string(idx);
}
