/***************************************************
Filename: HW3.java
Author: MIDN Ian Coffey (m261194)
Contain Class Reference for Mid
**************************************************/

// Import Libraries
import java.util.*;

public class HW3 
{
    // Method to Create Mid
    public static Mid createMid(Scanner in) 
    {
        // New Mid Object
        Mid newMid = new Mid();

        // Read Alpha
        newMid.alpha = in.next();
        newMid.firstName = in.next();
        newMid.lastName = in.next();
        newMid.company = in.nextInt();

        // Return New Mid
        return newMid;
    }

    // Method to Print Mid Info
    public static void printMid(Mid mid) 
    {
        System.out.println(mid.alpha + " " + mid.firstName + " " + mid.lastName + " " + mid.company);
    }

    public static void main(String [] args) 
    {
        // Variable & Object Declaration
        Scanner in = new Scanner(System.in);
        int midsCreate;
        int midsPrint;

        // Prompt User Input
        midsCreate = in.nextInt();

        // Create Array of Mids
        Mid[] midsList = new Mid[midsCreate];

        // For Loop to Read & Store Information
        for (int i = 0; i < midsCreate; i++) 
        {
            midsList[i] = new Mid();

            // Create New Mid
            midsList[i] = createMid(in);
        }   

        // Prompt User for Printed Mids Number
        midsPrint = in.nextInt();

        // Use For Loop To Output Information
        for (int j = 0; j < midsCreate; j++) 
        {
            if (midsList[j].company == midsPrint) 
            {
                printMid(midsList[j]);
            }
        }
    }
}