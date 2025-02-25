
import java.util.*;
import java.io.*;

public class HW1
{
  public static void main(String[] args) throws Exception
  {
    LineNumberReader lnr = null;
    Reader r = null;
    Scanner sc = null;

    try 
    {
        if (args.length > 0) 
        {
            lnr = new LineNumberReader(new FileReader(args[0]));
            sc = new Scanner(lnr);

        } else {
            r = new InputStreamReader(System.in);
            sc = new Scanner(r);
        }

    } catch (IOException e)  
    {
        System.out.println("File \"" + args[0] + "\" not found");
        System.exit(0);

    } 
    
    try 
    {
        System.out.println(Mystery.compute(sc));

    } catch(Exception e)
    {
        int lineNum = lnr.getLineNumber();
        System.out.println("Error " + e.getMessage() + " at line " + lineNum);
        System.exit(0);
    }
  }
}