/*************************************************
Filename: Shift.java
Author: MIDN Ian Coffey (m261194)
Hasher Provides the Shitf+Vigenere/Caesar Algorithms
**************************************************/

// Import Libraries
import java.util.*;

public class Shift implements Hasher
{
    // Variable Declarations
    private String cipher;

    // Public Constructor
    public Shift(String cipher) 
    {  
        this.cipher = cipher;
    }

    // Method to Return Hash Name
    public String getHashName() 
    { 
        return "shift+" + cipher;
    }

    // Method To Validate Key
    public void init(char[] key) throws ASCIIBoundsException 
    {
        // Ensure that No Character Exceeds Bounds
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key");
        }
    }

    // Method To Caesar/Vigerene Shift Plaintext
    public String hash(char[] plain) throws ASCIIBoundsException
    {
        // Priavte Key
        char[] x = "GO_NAVY_2018^mid".toCharArray();

        // Validate that There are No ASCII Bound Errors
        for (int i = 0; i < plain.length; i++) 
        {
            if (plain[i] < 42 || plain[i] > 122) 
                throw new ASCIIBoundsException("error " + plain[i] + " not allowed in key");
        }
    
        // Shift Plain Left by K
        for (int j = 0; j < 16; j++) 
        {
            char c = x[j];
            int s = (int) c % 16;

            for (int i = 0; i < s; i++)
            {
                char temp = x[0];
                
                for (int k = 0; k < x.length - 1; k++) 
                {
                    x[k] = x[k+1]; // Shift Characters
                }

                // Put First Character In Back
                x[x.length - 1] = temp;
            }

            // Perform Given Shift
            Encryptor E = null;
            if (cipher.equals("caesar"))
                E = new Caesar();
            else
                E = new Vigenere();     
                        
            E.init(plain);
            x = (E.encrypt(new String(x))).toCharArray();
            
        }

        // Return Text As String
        return new String(x);
    }

    // Method That Takes A String & Returns Corresponding Hasher
    public static Hasher getNewHash(String hash) throws AlgorithmException
    {
        Hasher A = null;
        switch (hash) 
        {
            case "padcut":  
                A = new Padcut();
                break;
            case "shift+caesar":
                A = new Shift("caesar");
                break;
            case "shift+vigenere":
                A = new Shift("vigenere");
                break;
            default:
                throw new AlgorithmException("Error! Hash algorithm '" + hash + "' not supported.");
        }

        return A;
    }
}