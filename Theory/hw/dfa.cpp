// Filename: sol.cpp
// Author: MIDN Ian Coffey
// Demo a C++ Implementation of a DFA

// Import Libraries
#include <iostream>
#include <fstream>

using namespace std;

// DFA Function
bool m1(string s) 
{
    // States                  q0    q1    q2    q3    q4
    // Inputs                {a,b},{a,b},{a,b},{a,b},{a,b}
    const int delta[5][2] = {{1,3},{1,2},{2,2},{4,2},{2,4}}; // State Transitions (Results from Input)
    const bool W[5] = {false, true, false, false, true}; // Accepting States q1 & q4

    // Traverse String to Find Final State
    int state = 0;
    for (int i = 0; i < s.length(); i++) 
    {
        int realIndex = s[i] - 'a'; // Convert to Char
        state = delta[state][realIndex]; // Assign New States
    }

    // Return Final State
    return W[state];

}

// Main Function
int main(int argc, char** argv) 
{
    // Variable Declaration
    string s;

    // Read In String and Print M1 Output
    cin >> s;
    cout << (m1(s) ? "accept" : "reject") << endl;

    // End Program
    return 0;
}
