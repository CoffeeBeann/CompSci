#include <iostream>
using namespace std;

// each char in s is represented by the number in 0-28
// that is its index in string s.
const string s = "abcdefghijklmnopqrstuvwxyz._,";
int geti(char c); // go from character to index
char getc(int i); // go from index to character

int main() {
  // read e/d msg key, e.g. 'e hello 18'
  string dir, msg;
  int n = 29, key;
  cin >> dir >> msg >> key;
  
  for(int j = 0; j < msg.length(); j++) {
    int i = geti(msg[j]); // i is value of msg[j] in Z_29
    int ip = (i*key)% n;  // ip is i*key in Z_29
    cout << getc(ip);
  }
  cout << endl;
  
  return 0;
}

int geti(char c) {
   for(int i = 0; i < s.length(); i++)
     if (s[i] == c)
       return i;
   return -1;
}

char getc(int i) {
  return s[i];
}
