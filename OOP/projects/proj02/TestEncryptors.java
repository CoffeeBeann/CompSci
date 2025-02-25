/*************************************************
Filename: TestEncryptors.java
Author: MIDN Ian Coffey (m261194)
Matches I/O Functionality for Encryption
**************************************************/

// Import Libraries
import java.util.*;

public class TestEncryptors 
{
    public static void main(String[] args) throws Throwable 
    {
        // Create ArrayList of all supported encryptors
        ArrayList<Encryptor> E = new ArrayList<Encryptor>();
        E.add(new Clear());
        E.add(new Caesar());
        E.add(new Vigenere());

        // Get Algorithm, Password, & Message from User
        System.out.print("algorithm: ");
        String encalg = System.console().readLine();
        System.out.print("password : ");
        char[] password = System.console().readPassword();
        System.out.print("message  : ");
        String plaintext = System.console().readLine();
        
        // Find Index of Encryptor (Throw Exception If Not Found)
        int i = -1;
        try 
        { 
            while(!E.get(++i).getAlgName().equals(encalg)); 

        } catch(Exception e) {

            throw new Exception("Unknown algorithm '"+encalg+"'.");
        }

        // Encrypt, Decrypt, & Print Sumamry of Results
        E.get(i).init(password);
        String ciphertext = E.get(i).encrypt(plaintext);
        String hopefully = E.get(i).decrypt(ciphertext);
        System.out.println("plain : " + plaintext);
        System.out.println("cipher: " + ciphertext);
        System.out.println("decryp: " + hopefully);
    }
}