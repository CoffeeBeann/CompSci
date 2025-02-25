/*************************************************
Filename: Lab1c.java
Author: MIDN Ian Coffey
Run Simple Lottery Odds Calculator
**************************************************/

// Import Libraries
import java.util.*;

public class Lab1c
{
    // Factorial Method
    public static int factorial(int k) 
    {
        // Base Case
        if (k == 1) 
        {
            return 1;

        } else {

            // Recursive Case
            return k * factorial(k-1);
        }
    }

    // Method to Calculate Numerator
    public static int numerator(int n, int k) 
    {
        // Calculate Numerator
        int numerator = n;
        for (int i = 1; i < k; i++) 
        {
            numerator *= (n - i);
        }

        // Return Number
        return numerator;
    }

    // Main Method
    public static void main (String [] args) 
    {
        // Variable & Object Declarations
        Scanner in = new Scanner(System.in);

        // Read In Information From args[]
        String name = "";
        for (int i = 0; i < args.length - 2; i++) 
        {
            name += args[i];

            if (!(i == args.length - 3))
            {
                name += " ";
            }
        }
        
        int n = Integer.parseInt(args[args.length - 2]);
        int k = Integer.parseInt(args[args.length - 1]);

        // Calculate Denominator
        int denominator = factorial(k);

        // Calculate Numerator
        int numerator = numerator(n, k);

        // Caclulate Odds
        double odds = (double) numerator / (double) denominator;

        // Output Results
        System.out.println("The two ints were " + n + " and " + k);
        System.out.println("numerator = " + numerator);
        System.out.println("denomintor = " + denominator);
        System.out.println("odds = 1 in " + (int) odds + " = " + (double) 1 / odds);
        System.out.println("Goodbye " + name + ".");

    }
}