import java.util.*;
public class Ex2
{
 public static Random r = new Random();
 public static void main(String[] args)
 {
 Apesha a = new Rita();
 Apesha b = new Aresqua();
 Apesha c = r.nextBoolean() ? new Ectra() : new Rita();
 Apesha d = r.nextBoolean() ? new Ectra() : new Aresqua();
 Ectra e = r.nextBoolean() ? new Ectra() : new Aresqua();

 a.abela();

 // Here!
 // Note: the ? : construct is an "if" as an expression.
 // so r.nextBoolean() ? new Ectra() : new Aresqua() will
 // evaluate to a new Ectra 50% of the time, and a new
 // Aresqua the other 50% of the time.
 }
}
