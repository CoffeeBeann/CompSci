/*************************************************
Filename: Vault.java
Author: MIDN Ian Coffey (m261194)
Request Authentication From User Given A Vault of Keys
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class Vault
{
    public static void main(String [] args) throws Exception
    {
        // Variable Declarations
        boolean auOption = false;
        Scanner sc = null;
        int fileArg = 0;

        // Check if File Is Given
        if (args.length == 0) 
        {
            System.out.println("usage: java Vault [-au] <filename>");
            System.exit(1);
        }

        // Check For AU option
        if (args[0].equals("-au")) 
        {
            auOption = true;
            fileArg = 1;
        }

        // Try to Open File
        try 
        {
            sc = new Scanner(new FileReader(args[fileArg]));

        } catch (Exception E) 
        {
            System.out.println("Error! File '" + args[fileArg] + "' could not be opened.");
            System.exit(1);
        }

        // Read File & Store Users in ArrayList
        ArrayList<User> E = null;
        try
        {
            E = User.read(sc);

        } catch (FormatException e) 
        {
            System.out.println("Error! File '" + args[fileArg] + "' improperly formatted.");
            System.exit(1);
        }

        // Read Username & Password from User
        System.out.print("username: ");
        String name = System.console().readLine();
        System.out.print("password: ");
        char[] password = System.console().readPassword();

        // Check For Add User Option
        if (auOption) 
        {
            // Read Hash Algorithm From User
            System.out.print("Hash algorithm: ");
            String hashAlg = System.console().readLine();

            try 
            {
                // Create New User & Add to ArrayList
                User addUser = new User("user", name, hashAlg, password); 

                // Add User to File
                User.addToFile(E, addUser, args[fileArg]);

            } catch (AlgorithmException e) 
            {
                System.out.println(e.getErrorMessage());
                System.exit(1);

            } catch (ASCIIBoundsException e) 
            {
                System.out.println("Error! Invalid symbol '" + e.getBadChar() + "' in password.");
                System.exit(1);

            } catch (FormatException e) 
            {
                System.out.println(e.getErrorMessage());
                System.exit(1);
            }

        } else {

            for (int i = 0; i < E.size(); i++) 
            {
                try 
                {
                    // Check For Match
                    if (E.get(i).match(name, password)) 
                    {
                        // Access Granted
                        System.out.println("Access granted!");
                        
                        // Local Variable Declaration for While Loop
                        String cmd = "";
                        Scanner stdin = new Scanner(System.in);

                        while (!cmd.equals("quit")) 
                        {
                            System.out.print("> ");
                            cmd = stdin.next();

                            try 
                            {
                                if (cmd.equals("labels")) 
                                {
                                    // Read Out All Labels of User
                                    E.get(i).readLabel(E, password, args[fileArg], "", true);

                                } else if (cmd.equals("get")) 
                                {
                                    // Read Label To Retrieve
                                    String label = stdin.next();

                                    // Read Out Label
                                    E.get(i).readLabel(E, password, args[fileArg], label, false);

                                } else if(cmd.equals("add"))
                                {
                                    // Grab Data To Add to File
                                    String enc = stdin.next();
                                    String lab = stdin.next();
                                    String info = stdin.next();

                                    // Add Data to File 
                                    E.get(i).addData(E, password, enc, lab, info, args[fileArg]);
                                    

                                } else 
                                {
                                    if (!cmd.equals("quit"))
                                        System.out.println("Unknown command '" + cmd + "'." );
                                }

                            } catch (AlgorithmException e) 
                            {
                                System.out.println(e.getErrorMessage()); 

                            } catch (FormatException e) 
                            {
                                System.out.println(e.getErrorMessage());

                            } catch (ASCIIBoundsException e) 
                            {
                                System.out.println(e.getErrorMessage());
                            }

                        }

                        // Exit Program
                        System.exit(0);
                    } 
                
                } catch (ASCIIBoundsException e) 
                {
                    System.out.println("Access denied!");
                    System.exit(0);

                } catch (AlgorithmException e) 
                {
                    System.out.println(e.getErrorMessage()); 
                    System.exit(0);
                }
            }

            System.out.println("Access denied!");
            System.exit(0);

        }
    }
}