/*************************************************
Filename: Clear.java
Author: MIDN Ian Coffey (m261194)
Encryptor Provides the Clear Algorithm
**************************************************/

public class Clear implements Encryptor 
{
    // Method To Return Encryptor Name
    public String getAlgName() { return "clear"; }

    public void init(char[] key) throws ASCIIBoundsException 
    { 
        // Check for ASCII Error
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key");
        }
    }

    // Method To Encrypt A Given Text ()
    public String encrypt(String plain) throws ASCIIBoundsException 
    { 
        // Convert PlainText to Char Array
        char[] cipher = plain.toCharArray();
        
        for (int i = 0; i < cipher.length; i++) 
        {
            // Check for ASCII Bounds Error
            if (cipher[i] < 42 || cipher[i] > 122) 
                throw new ASCIIBoundsException("error " + cipher + " not allowed in plaintext");
        }

        return plain; 
    }

    // Method To Return Plaintext (Clear does No Encryption)
    public String decrypt(String cipher){ return cipher; }

    // Method That Takes A String & Returns Corresponding Encyrptor
    // I Didn't Know Where Else To Put This 
    public static Encryptor getNewEncryptor(String enc) throws AlgorithmException
    {
        Encryptor A = null;
        switch (enc) 
        {
            case "clear":  
                A = new Clear();
                break;
            case "caesar":
                A = new Caesar();
                break;
            case "vigenere":
                A = new Vigenere();
                break;
            default:
                throw new AlgorithmException("Error! Encryption algorithm '" + enc + "' not supported.");
        }

        return A;
    }
}