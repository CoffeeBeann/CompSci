/*************************************************
Filename: Caesar.java
Author: MIDN Ian Coffey (m261194)
Encryptor Provides the Caesar Algorithm
**************************************************/

public class Caesar implements Encryptor 
{
    // Local Variable Declarations
    private int shiftChar;

    public String getAlgName() { return "caesar"; }

    public void init(char[] key) throws ASCIIBoundsException
    {
        // Ensure that ASCII is Within Bounds
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key");
        }

        // Sum All Characters in Key
        for (int i = 0;  i < key.length; i++) 
        {
            shiftChar += (int) (key[i]) - 42;
        }

        // Calculate Shift Character
        shiftChar += 18;
        shiftChar = shiftChar % 81;
        shiftChar += 42;
    }

    public String encrypt(String plain) throws ASCIIBoundsException 
    {
        // Convert String to Char[]
        char[] cipher = plain.toCharArray(); 

        // Encrypt Message With Key
        for (int i = 0; i < cipher.length; i++) 
        {
            // Check for ASCII Bounds Error
            if (cipher[i] < 42 || cipher[i] > 122) 
                throw new ASCIIBoundsException("error " + cipher[i] + " not allowed in plaintext");
            
            int k  = shiftChar - 42;
            int p  = cipher[i] - 42;
            int c  = (p + k) % 81;
            int cc = 42 + c;
            cipher[i] = (char) cc;
        }

        // Convert Back to String & Return
        return new String(cipher);
    }

    public String decrypt(String cipher)
    { 
        // Convert String to Char[]
        char[] decrypt = cipher.toCharArray();

        // Encrypt Message With Key
        for (int i = 0; i < decrypt.length; i++) 
        {
            int k  = shiftChar - 42;
            int c  = (int) decrypt[i] - 42;
            int p  = (c + (81 - k)) % 81;
            int pc = 42 + p;
            decrypt[i] = (char) pc;
        }

        // Convert Back to String & Return 
        return new String(decrypt);
    }
}