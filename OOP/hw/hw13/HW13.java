/***************************************************
Filename: HW13.java
Author: MIDN Ian Coffey (m261194)
Take in three Numbers & Perform mathematic operation
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class HW13 
{
    public static void main(String[] args) throws Exception
    {
        int x = 0, k = 0, m = 0, r = 0;

        boolean verbose = args.length > 0 && args[0].equals("-v");
        Scanner sc = new Scanner(System.in);
        try {
            if (verbose)
            System.out.print("Enter x, k, m: ");
            x = sc.nextInt();
            k = sc.nextInt();
            m = sc.nextInt();
            if (m == 0 || k < 0) { throw new Exception();}
            r = MyMath.modexp(x,k,m);
        } catch (Exception e) {
            if (verbose) {
                System.out.println("Error in HW13! invalid input.");
            }
            System.exit(1);
        }
        if (verbose)
        System.out.print(x + "^" + k + " % " + m + " = ");
        System.out.println(r);
    }
}