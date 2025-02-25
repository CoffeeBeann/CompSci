/***************************************************
Filename: HW02.java
Author: MIDN Ian Coffey (m261194)
Read In User Phrase and Output it in Columns
**************************************************/

// Import Libraries
import java.util.*;

public class HW02 
{

    // Max Length Method
    public static int maxLength(String[] List) 
    {
        int max = 0;

        // Traverse List To Find Longest Word
        for (int i = 0; i < List.length; i++) 
        {
            if (List[i].length() > max) 
            {
                max = List[i].length();
            }
        }

        return max;
    }

    // Main Method
    public static void main(String [] args) 
    {
        // Variable & Scanner Object Declaration
        Scanner in  = new Scanner(System.in);
        String newWord = "";
        int count = 0;

        // Read User Input
        int phraseLength = in.nextInt();
        String[] wordList = new String[phraseLength];
        
        // Store Words in Array
        for (int i = 0; i < phraseLength; i++) 
        {
            wordList[i] = in.next();
        }

        // Output Words In Column Format
        for (int i = 0; i < maxLength(wordList); i++) 
        {
            for (int j = 0; j < phraseLength; j++) 
            {
                // Determine is Word Needs To Be Printed or Space
                if (wordList[j].length() <= i ) 
                {
                    System.out.print("  ");
            
                } else {

                    System.out.print(wordList[j].charAt(i) + " ");
                }

            }
            
            // New Line
            System.out.println();
        }

    }
}
