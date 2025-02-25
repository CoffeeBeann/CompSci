#include <iostream>
using namespace std;

/* 
Extended Eucliden gcd
  inputs : u, v where u >= 0 and v >= 0
  outputs: g,s,t such that g = s*u + t*v and g is gcd(u,v)
*/
int egcd(int u, int v, int& s, int& t);

int main() {
  int x, n;
  cin >> x >> n;
  if (x < 0 || x >= n || n <= 1) {
    cout << "usage: enter <x> <n> where x >= 0 and n > 1 and n > x" << endl
	 << "       computes d such that x*d mod n = 1" << endl;
    return 1;
  }
  int g, s, t;
  g = egcd(x,n,s,t);
  if (g != 1)
    cout << "no multiplicative inverse!" << endl;
  else {
    int d = (s >= 0 ? s : n + s);
    cout << x << "*" << d << " = 1 in Z_" << n << endl;
  }
  return 0;
}

int egcd(int u, int v, int& s, int& t) {
  if (v == 0) {
    s = 1;
    t = 0;
    return u;
  }
  else {
    int q = u/v, r = u%v, sp, tp;
    int g = egcd(v,r,sp,tp);
    s = tp;
    t = sp-tp*q;
    return g;
  }
}