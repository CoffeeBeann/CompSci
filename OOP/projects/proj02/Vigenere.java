/*************************************************
Filename: Vigenere.java
Author: MIDN Ian Coffey (m261194)
Encryptor Provides the Vigenere Algorithm
**************************************************/

public class Vigenere implements Encryptor 
{
    // Local Variables
    private int shiftChar;
    private char[] key;

    // Method To Return Encryptor Name
    public String getAlgName() { return "vigenere"; }

    // Method To Validate & Save Key
    public void init(char[] key) throws ASCIIBoundsException
    {
        // Ensure that ASCII is Within Bounds
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key");
        }

        this.key = key;
    }

    // Method To Vigenere Shift Plaintext
    public String encrypt(String plain) throws ASCIIBoundsException
    { 
        // Convert String to Char[]
        char[] cipher = plain.toCharArray();

        for (int i = 0; i < cipher.length; i++) 
        {
            // Check for ASCII Bounds Error
            if (cipher[i] < 42 || cipher[i] > 122) 
                throw new ASCIIBoundsException("error " + cipher + " not allowed in plaintext");
            
            this.shiftChar = (int) key[i % key.length];
            int k = shiftChar - 42;
            int p = cipher[i] - 42;
            int c = (p + k) % 81;
            int cc = 42 + c;
            cipher[i] = (char) cc;
        }

        // Convert Back to String & Return
        return new String(cipher);
    }

    // Method To Decrypt Vigenere Cipher
    public String decrypt(String cipher)
    { 
        // Convert String to Char[]
        char[] decrypt = cipher.toCharArray();

        for (int i = 0; i < decrypt.length; i++) 
        {
            shiftChar = key[i % key.length];
            int k = shiftChar - 42;
            int c = decrypt[i] - 42;
            int p = (c + (81 - k)) % 81;
            int pc = 42 + p;
            decrypt[i] = (char) pc;
        }

        // Convert Back to String 
        return new String(decrypt);
    }
}