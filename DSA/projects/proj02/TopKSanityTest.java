import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class TopKSanityTest {
  @Test
  public void add4get3() {
    TopK<String> tt = new TopK<>(3);
    tt.add("what");
    tt.add("is");
    tt.add("the");
    tt.add("what");
    List<String> top = tt.getTop();
    assertEquals(3, top.size());
    assertEquals("what", top.get(0));
    assertEquals("what", top.get(1));
    assertEquals("the", top.get(2));
  }

  @Test
  public void add3get5() {
    TopK<Integer> tt = new TopK<>(5);
    tt.add(5);
    tt.add(10);
    tt.add(2);
    List<Integer> top = tt.getTop();
    assertEquals(3, top.size());
    assertEquals(10, (int)top.get(0));
    assertEquals(5, (int)top.get(1));
    assertEquals(2, (int)top.get(2));
  }
}
