/***************************************************
Filename: HW5.java
Author: MIDN Ian Coffey (m261194)
Utilize Countdown.java to Implement Counter
**************************************************/

// Import Libraries
import java.util.*;

public class HW5 
{
    public static void main(String [] args) 
    {
        // Variable & Object Declarations
        Countdown countBefore = new Countdown(99,0);
        Countdown countAfter = new Countdown(98,0);

        // Run Countdown
        String phraseBeforeNoCamel;
        String phraseBefore;
        String phraseAfter;

        for (int i = 99; i > 0; i--) 
        {
            phraseBefore = countBefore.next();
            phraseBeforeNoCamel = phraseBefore;
            phraseAfter = countAfter.next();

            // Camel Case Strings
            String camel = phraseBefore.substring(0,1);
            camel = camel.toUpperCase();
            String rest = phraseBefore.substring(1,phraseBefore.length());
            phraseBefore = camel + rest;

            // Check for 1 & 2 Case
            if (i == 1) 
            {
                System.out.println("One bottle of beer on the wall, one " +
                "bottle of beer.\nTake one down and pass it around, no more " +
                "bottles of beer on the wall.\n");   

            } else  if (i == 2) 
            {
                System.out.println("Two bottles of beer on the wall, two " +
                "bottles of beer.\nTake one down and pass it around, one " +
                "bottle of beer on the wall.\n");   
            
            } else {
            
                System.out.println(phraseBefore  + " bottles of beer on the wall, " + phraseBeforeNoCamel  +
                " bottles of beer.\nTake one down and pass it around, " + phraseAfter + 
                " bottles of beer on the wall.\n");    
            }
        }
    }
}