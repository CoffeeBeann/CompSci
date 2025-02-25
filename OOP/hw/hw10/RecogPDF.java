/**
 * PDF file header is %PDF In Hex
 * This recognizer checks whether the first few bytes
 * match this header.
 */
public class RecogPDF extends RecogASCII
{
  // Header Array
  private static int[] header = 
  { 0x25, 0x50, 0x44, 0x46};
  // 0     1     2     3

  private int i = 0;

  public String getName() { return "PDF"; }

  public void feed(int nextByte)
  {
    if (getState() == 2) // 2 : unkown
    {
      if (header[i] >= 0 && nextByte != header[i])
	setState(0); // 0 : not match
    }
    i++;
    if (i > 3 && getState() != 0)
      setState(1); // 1 : match
  }
  boolean decision() { return getState() == 1; }
}