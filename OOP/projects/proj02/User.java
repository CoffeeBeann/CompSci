/*************************************************
Filename: User.java
Author: MIDN Ian Coffey (m261194)
Object & Methods Definitions for User Class
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class User
{
    // Variable Declarations 
    private String type;
    private String username;
    private String encalg;
    private char[] key;

    // Default Constructor Method
    public User(String type, String username, String encalg, char[] key) throws Exception
    {
        // Save Username & Hash
        this.type = type;
        this.username = username;
        this.encalg = encalg; 

        // Validate Key
        for (int i = 0; i < key.length; i++) 
        {
            if (key[i] < 42 || key[i] > 122) 
                throw new ASCIIBoundsException("error " + key[i] + " not allowed in key", key[i]);
        }

        // Save Key Once Validated
        this.key = key;
    }

    // Method to Read Users From File & Return ArrayList
    public static ArrayList<User> read(Scanner sc) throws Exception
    {
        // Create ArrayList for Return
        ArrayList<User> rtn = new ArrayList<User>();

        // Read From File & Check for Fomat Errors
        while (sc.hasNext()) 
        {
            // Temporary Strings For User Creation
            String type, user, alg, pswd;

            // Read "User" or "Datas" String from File
            type = sc.next();

            if (!type.equals("user") && !type.equals("data"))
                throw new FormatException("Error! Improper File Format");
            
            // Read User Name
            user = sc.next();

            // Read Algorithm
            alg = sc.next();

            // Read Key
            pswd = sc.next();

            // Create New User & Add to Array List
            rtn.add(new User(type, user, alg, pswd.toCharArray())); // Can Throw AlgorithmException
        }

        // Return ArrayList
        return rtn;

    } 

    // Method to Check If Given Credentials Match User Values
    public boolean match(String user, char[] pass) throws Exception
    {
        // Return False If Username Does Not Match
        if (!user.equals(this.username) || (this.type).equals("data")) 
            return false;
        
        // Create & Specialize Hasher
        Hasher A = Shift.getNewHash(this.getAlgName());
        
        // Hash Given Password  
        A.init(pass);
        String hashedPassword = A.hash(pass);

        // Return T or F
        return (hashedPassword.equals(new String(key)));
    }

    // Method to Add User to Given File
    public static void addToFile(ArrayList<User> T, User newUser, String filename) throws Exception
    {
        // Create & Specialize Hasher if Adding User
        Hasher A = Shift.getNewHash(newUser.encalg);
        
        // Hash Given Password
        A.init(newUser.key);
        newUser.key = (A.hash(newUser.key)).toCharArray();

        // Validate Username
        String nameCheck = newUser.username;
        for (int i = 0; i < T.size(); i++) 
        {
            if (nameCheck.equals(T.get(i).username)) 
                throw new FormatException("Error! Username '" + nameCheck + "' already in use.");
        }   

        // Add User To ArrayList
        T.add(newUser);

        // Create Print Writer
        PrintWriter pw = null;
        try 
        {
            pw = new PrintWriter(new File(filename));

        } catch (FileNotFoundException fnfe) 
        {
            fnfe.printStackTrace();
        } 

        // Print All User Objects to File
        for (int j = 0; j < T.size(); j++) 
        {
            pw.println(T.get(j).toString());
        }

        // Close Printer
        pw.close();

    }

    // Method to Add Data to Given File
    public void addData(ArrayList<User> T, char[] password, String encryption, String label, String info, String fname) throws Exception
    {
        // Check Label For ASCII Errors
        char[] check = label.toCharArray();
        for (int i = 0; i < check.length; i++) 
        {
            if (check[i] < 42 || check[i] > 122 || check[i] == 95)
                throw new ASCIIBoundsException("Error! Label '" + label + "' is invalid.");
        }

        // Check Info for ASCII Errors
        check = info.toCharArray();
        for (int i = 0; i < check.length; i++) 
        {
            if (check[i] < 42 || check[i] > 122)
                throw new ASCIIBoundsException("Error! Invalid character '" + check[i] + "' in text.");
        }

        // Create & Specialize Encryptor if Adding User (Throws AlgorithmException)
        Encryptor A = Clear.getNewEncryptor(encryption);

        // Concatenate Label & Info
        String plainTxt = label + "_" + info;

        
        // Encrypt Given Information
        A.init(password);
        String cipherTxt = A.encrypt(plainTxt);

        // Create New Data & Add to File
        User newData = new User("data", this.username, encryption, cipherTxt.toCharArray());

        T.add(newData);

        // Create Print Writer
        PrintWriter pw = null;
        try 
        {
            pw = new PrintWriter(new File(fname));

        } catch (FileNotFoundException fnfe) 
        {
            fnfe.printStackTrace();
        } 

        // Print All User Objects to File
        for (int j = 0; j < T.size(); j++) 
        {
            pw.println(T.get(j).toString());
        }

        // Close Printer
        pw.close();
    }

    // Method To Search & Return Specific Data of Label
    public void readLabel(ArrayList<User> T, char[] password, String fname, String label, boolean readAll) throws Exception
    {
        // Object Declaration
        Encryptor A = null;
        Scanner sc = null;

        // Open File
        try 
        {
            sc = new Scanner(new FileReader(fname));

        } catch(Exception e) 
        {
            e.printStackTrace();
            System.exit(1);
        }

        // Search for Match
        for (int i = 0; i < T.size(); i++) 
        {
            // Read "User" or "Data" from File
            String type = sc.next();
            String name = sc.next();

            // Read Shift & Hash if Match
            if (type.equals("data") && name.equals(this.username))
            {
                try
                {
                    String enc = sc.next();
                    String cipherTxt = sc.next();

                    // Create New Encryptor Object
                    A = Clear.getNewEncryptor(enc);

                    // Decrypt CipherText
                    A.init(password);
                    String decrypt = A.decrypt(cipherTxt);

                    // Parse String For Label
                    char[] parse = decrypt.toCharArray();
                    String labelCheck = ""; 
                    String finalLabel = "";

                    for (int j = 0; j < parse.length; j++) 
                    {
                        // Check for '_'
                        if (parse[j] == 95) 
                        {
                            if (readAll)
                            {
                                finalLabel = decrypt.substring(0,j);

                                // Throw Error if no _ was found
                                if (finalLabel.equals(""))
                                    throw new FormatException("Error! corrupted entry '" + cipherTxt + "' in vault file.");
                            
                                break;

                            } else 
                            {
                                labelCheck = decrypt.substring(0,j);

                                // Check If Label Matches
                                if (labelCheck.equals(label)) 
                                {
                                    // Parse Final Label
                                    finalLabel = decrypt.substring(j + 1, parse.length);
                                    System.out.println(finalLabel);

                                    // Throw Error if no _ was found
                                    if (finalLabel.equals(""))
                                        throw new FormatException("Error! corrupted entry '" + cipherTxt + "' in vault file.");
                                    
                                    // Break Loop
                                    i = T.size();
                                    break;
                                }
                            }       
                        }
                    }

                    // Output label
                    if (readAll) 
                    {
                        // Throw Error if no _ was found
                        if (finalLabel.equals(""))
                            throw new FormatException("Error! corrupted entry '" + cipherTxt + "' in vault file.");
                            
                        System.out.println(finalLabel);
                    }

                } catch (AlgorithmException e) 
                {
                    System.out.println(e.getErrorMessage());
                } 
            }
            else 
            {
                // Trash Line
                sc.nextLine();
            }
        }
    }

    // Method to Return A Users Hash Algorithm
    public String getAlgName() 
    {
        return this.encalg;
    }

    // Method to Output User Data
    public String toString() 
    {
        String tmp = new String(this.key);
        return this.type + " " + this.username + " " + this.encalg + " " + tmp;
    }

    // Main Method for Testing
    public static void main(String [] args) throws Exception
    {     
        // Check for Arguments
        if (args.length == 0) 
        {
            System.out.println("usage java User <filename>");
            System.exit(1);
        }

        // Scanner Decaration
        Scanner in = null;
        try 
        {
            in = new Scanner(new FileReader(args[0]));
        } catch (IOException e) 
        {
            System.out.println("");
            System.exit(1);
        }

        try 
        {
            // Variable & Array Declarations
            ArrayList<User> T = read(in);

            // Test toString()
            for (int i = 0; i < T.size(); i++) 
            {
                System.out.println(T.get(i).toString());
            }

        } catch (FormatException e) 
        {
            System.out.println("Error! File '" + args[0] + "' improperly formatted.");
            System.exit(1);

        } catch (AlgorithmException e) 
        {
            System.out.println(e.getErrorMessage());
            System.exit(1);
        }
    }
}