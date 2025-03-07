/*************************************************
Filename: lab08.cpp
Author: MIDN Ian Coffey
Perform Hang Man Game Functions
**************************************************/

// Import Libraries
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>

using namespace std;

// Function Prototypes
void printSpaced(string s);
string mkShadowString(string s);
string uncover(string original, string covered, char c);
string crossOut(char c, string s);

int main()
{
    cout << "Testing printSpaced..." << endl;
    cout << "["; 
    printSpaced("wash"); 
    cout << "]" << endl;

    cout << "["; 
    printSpaced("HelloWorld!"); 
    cout << "]" << endl;   

    cout << endl << "Testing mkShadowString..." << endl;
    string s1 = "food";
    string s2 = mkShadowString(s1);
    cout << "original: " << s1 << endl;
    cout << "  shadow: " << s2 << endl;
    string s3 = "require";
    string s4 = mkShadowString(s3);
    cout << "original: " << s3 << endl;
    cout << "  shadow: " << s4 << endl;
            
    cout << endl << "Testing uncover..." << endl;
    string u1 = "housepet";
    string u2 = "________";
    cout << u1 << endl << u2 << endl;
        
    string u3 = uncover(u1,u2,'e');
    if( u2 == u3 )
        cout << u3 << " there were not e's!"  << endl;
    else
        cout << u3 << " there were e's!"  << endl;
    u2 = u3;
        
    u3 = uncover(u1,u2,'q');
    if( u2 == u3 )
        cout << u3 << " there were not q's!"  << endl;
    else
        cout << u3 << " there were q's!"  << endl;

    cout << endl << "Testing crossOut..." << endl;
    string t1 = "Go get good food!";
    cout << t1 << endl;
    t1 = crossOut('o',t1);
    cout << t1 << endl;
    t1 = crossOut('g',t1);
    cout << t1 << endl;
        
    return 0;
}

void printSpaced(string s) 
{
    // Traverse Length of String with For Loop
    for (int i = 0; i < s.length(); i++) 
    {
        // Determine If Space Is Needed After Chartacter
        if (i == s.length() - 1) 
        {
            cout << s[i];

        } else {

            cout << s[i] << " ";
        }   
    }
}

string mkShadowString(string s) 
{
    // Declare New String to Send Back to Main
    string shadow = "";

    // Traverse Length of String To Set To _'s
    for (int i = 0; i < s.length(); i++) 
    {
        shadow += "_";
    }

    return shadow;
}

string uncover(string original, string covered, char c) 
{
    // Traverse String to Check for Matches
    for (int i = 0; i < covered.length(); i++) 
    {
        if (original[i] == c) 
        {
            covered[i] = c;
        } 
    }

    return covered;
}
string crossOut(char c, string s) 
{
    // Traverse String to Check for Matches
    for (int i = 0; i < s.length(); i++) 
    {
        if (s[i] == c) 
        {
            s[i] = '*';
        }
    }

    return s;
}
