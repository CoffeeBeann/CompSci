public class Ex1 {
  private static int modadd(int x, int y, int n) {
    System.out.println("in modadd!");
    int res = x + y % n;
    return res;
  }
  public static void main(String[] args) {
    int k = modadd(4,5,0);
    System.out.println(k);
  }
}
