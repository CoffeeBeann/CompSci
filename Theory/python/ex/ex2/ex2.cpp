#include <iostream>
using namespace std;

int foo(int x) {
  int res = x + y;
  return res;
}

int main() {
  int r = foo(42);
  cout << r << endl;
}
