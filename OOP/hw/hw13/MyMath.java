public class MyMath
{
  /**
  * Returns x^k mod m
  * Note: k must be non-negative, and m must be positive
  */
  public static int modexp(int x, int k, int m)
  {
    int r = 1;
    for(int i = 0; i < k; i++)
      r = r*x % m;
    return r;
  }
}