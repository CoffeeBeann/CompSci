public class Ex3 {
  public static int foo(int x, int y) {
    int res = x * y;
    return res;
  }

  public static int bar(string s) {
    String[] A = s.split(":");
    return foo(A[0],A[1]);
  }

  public static void main(String[] args) {
    System.out.println(
        "Testing foo ... " + foo(6,7));
    System.out.println(
        "Testing bar ... " + bar("6:7"));
  }
}

  
