/*************************************************
Filename: Padcut.java
Author: MIDN Ian Coffey (m261194)
Hasher Provides the Padcut Algorithm
**************************************************/

// Import Libraries
import java.util.*;

public class Padcut implements Hasher
{
    // Method to Return Hasher Name
    public String getHashName() { return "padcut"; }

    // Method to Validate Key
    public void init(char[] key) throws ASCIIBoundsException 
    {
        // Ensure that No Character Exceeds Bounds
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key");
        }
    }

    // Method To Padcut Hash Plaintext
    public String hash(char[] plain) throws ASCIIBoundsException
    {
        char[] rtn = new char[16];

        // Check if Length >= 16
        if (plain.length >= 16) 
        {
            for (int i = 0; i < 16; i++) 
            {
                rtn[i] = plain[i];
            }
            return new String(rtn);
        }

        // Convert String to Char[] & Copy to Return
        for (int i = 0; i < plain.length; i++) 
        {
            rtn[i] = plain[i];
        } 

        // Add X's to Back
        for (int j = plain.length; j < 16; j++) 
        {
            rtn[j] = 'x';
        }

        // Return Hash as String
        return new String(rtn);
    }
}