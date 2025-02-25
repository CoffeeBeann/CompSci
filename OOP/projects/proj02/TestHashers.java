/*************************************************
Filename: TestHashers.java
Author: MIDN Ian Coffey (m261194)
Matches I/O Functionality for Hashing
**************************************************/

// Import Libraries
import java.util.*;

public class TestHashers 
{
    public static void main(String[] args) throws Throwable 
    {
        // Create ArrayList of all supported Hashers
        ArrayList<Hasher> E = new ArrayList<Hasher>();
        E.add(new Padcut());
        E.add(new Shift("caesar"));
        E.add(new Shift("vigenere"));

        // Get Hash & Password from User
        System.out.print("algorithm: ");
        String encalg = System.console().readLine();
        System.out.print("password : ");
        char[] password = System.console().readPassword();

        // Find Index of Hasher (Throw Exception If Not Found)
        int i = -1;
        try 
        { 
            while(!E.get(++i).getHashName().equals(encalg)); 

        } catch(Exception e) {

            throw new Exception("Unknown algorithm '"+encalg+"'.");
        }

        // Hash & Print Sumamry of Results
        E.get(i).init(password);
        String hashPass = E.get(i).hash(password);
        String passRead = new String(password);
        System.out.println("password read : " + passRead);
        System.out.println("hash computed : " + hashPass);
    }
}