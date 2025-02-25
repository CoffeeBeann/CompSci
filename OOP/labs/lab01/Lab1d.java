/*************************************************
Filename: Lab1d.java
Author: MIDN Ian Coffey
Run Simple Lottery Odds Calculator
**************************************************/

// Import Libraries
import java.util.*;

public class Lab1d
{
    // Main Method
    public static void main (String [] args) 
    {
        // Variable & Object Declaration
        String [] insults = {"Damn you suck", "lol no", "get rekt", "yikes", "did your mom drop you as a kid?", "wrong", "nope", "I feel bad for you", "wow you're special"};
        Scanner in = new Scanner(System.in);
        Random rand = new Random(System.currentTimeMillis());
        int num = rand.nextInt(10);
        int guess = -1, count = 1;
        String newSlur;

        System.out.print("Guess a number between 0 and 10: ");

        while (guess != num) 
        {
            guess = in.nextInt();

            if (guess != num) 
            {
                newSlur = insults[rand.nextInt(8)];

                System.out.print(newSlur + " Guess again: ");
                count++;
            }
        }

        System.out.println("Right after " + count + " guesses!");
    }
}