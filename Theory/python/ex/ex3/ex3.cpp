#include <iostream>
using namespace std;

int foo(int x, int y) {
  int res = x * y;
  return res;
}

int bar(string s) {
  int n = s.find(":");
  return foo(s.substr(0,n),s.substr(n+1,s.length()));
}

int main() {
  cout << "Testing foo ... " << foo(6,7) << endl;
  cout << "Testing bar ... " << bar("6:7") << endl;
}
  
