// To compile: g++ a0.cpp -o a0 -O2 -larmadillo
// NOTE: the armadillo library defines types mat and vec,
// among others. The '*' operator does matrix-matrix or
// matrix-vector product, as you'd like it to.  If A is
// a matrix, A(i,j) gets element i,j, but be careful!
// Armadillo indexes starting at 0 rather than 1!
#include <cstdlib>
#include <iostream>
#include <armadillo> // library for linear algebra
using namespace std;
using namespace arma; // this is armadillo's namespace

bool inshape(double x, double y);

int main()
{
  // Here are some interesting 3x3 matrices given as a list of *row* vectors
  double a = 3.141596*.75;
  mat A = {{1,0,2},{0,1,1},{0,0,1}};
  mat B = {{cos(a),-sin(a),0},{sin(a),cos(a),0},{0,0,1}};
  mat C = {{3,0,0},{0,3,0},{0,0,1}};
  mat D = {{-1,0,0},{0,1,0},{0,0,1}};
  mat I = {{1,0,0},{0,1,0},{0,0,1}}; //-- what's this matrix?
  mat G = {{1,0,2},{0,1,1},{0,0,1}};

  // define our linear transformation (try lot's of different things!)
  mat T = -B*D*C; //<<<< Expect to change this!

  // generate shape points, apply T to them, write to tmp.gnuplot
  ofstream fout("tmp.gnuplot");
  int N = 200;
  for(int i = 0; i <= N; i++)
    for(int j = 0; j <= N; j++) {
      double x = i*(1.0/N), y = j*(1.0/N);
      if (inshape(x,y)) {
	vec u = {x,y,1.0};
	mat v = T*u; // apply linear transformation
	fout << v(0,0) << ' ' << v(1,0) << endl; // armadillo uses zero-indexing!
      }      
    }
  fout.close();

  // This craziness calls "gnuplot" to plot the points in tmp.gnuplot
  system("echo 'set title \"'$(date)'\";\
  set size square ; set xrange [-4:4]; set yrange [-4:4];\
  plot \"tmp.gnuplot\" with points pointtype 0 notitle' | gnuplot -p");

  return 0;
}

bool inshape(double x, double y) {
  const double epsilon = 0.0000001;
  return  (x-(1-.18))*(x-(1-.18)) + (y-.6)*(y-.6) > (.1)*(.1)+epsilon && (
	  (x-.5)*(x-.5) + (y-.3)*(y-.3) <= .12+epsilon ||
	  (x-.18)*(x-.18) + (y-.6)*(y-.6) <= (.18)*(.18)+epsilon ||
	  (x-(1-.18))*(x-(1-.18)) + (y-.6)*(y-.6) <= (.18)*(.18)+epsilon);
}
