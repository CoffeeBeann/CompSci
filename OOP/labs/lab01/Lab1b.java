/*************************************************
Filename: Lab1b.java
Author: MIDN Ian Coffey
Run Simple Lottery Odds Calculator
**************************************************/

// Import Libraries
import java.util.*;

public class Lab1b
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

        System.out.print("Enter your name: ");
        String name  = in.nextLine();

        System.out.print("Please input an integer ");
        int n = in.nextInt();

        System.out.print("Please input a second integer ");
        int k = in.nextInt();

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