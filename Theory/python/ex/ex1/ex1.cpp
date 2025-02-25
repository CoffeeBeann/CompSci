#include <iostream>
using namespace std;

int modadd(int x, int y, int n) {
  cout << "in modadd!" << endl;
  int res = x + y % n;
  return res;
}

int main() {
  int k = modadd(4,5,0);
  cout << k << endl;
}
