/*************************************************
Filename: Lab09a.java
Author: MIDN Ian Coffey (261194)
Implement Mini Shell With Error Handling
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class Lab09a
{
    public static void main(String[] args) throws QueueException
    {
        // Variable Declarations
        Scanner sc = new Scanner(System.in);
        ModQueue Q = new ModQueue();
        boolean verbose = false;
        boolean stdout = true;

        // Traverse args for File objects
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-v"))
            {
                verbose = true;

            } else
            {
                try
                {
                    sc = new Scanner(new FileReader(args[i]));
                    stdout = false;

                } catch (IOException e)
                {
                    System.out.println("File '" + args[i] + "' could not be opened; switching input to standard in.");
                    stdout = true;
                }
            }
        }

        do
        {
            // Local Variable Declaration
            String s = "";

            if (stdout)
                System.out.print("> ");

            try 
            {
                s = sc.next();

            } catch (Exception e) 
            {
                System.out.println("Unexpected end of input.");
                break;
            }

            String next = "";

            if (s.equals("quit"))
                break;

            else if (s.equals("clearto"))
            {
                try
                {
                    next = sc.next();
                    Q.dequeue(next);

                } catch (QueueException e)
                {
                    if (verbose)
                    {
                        System.out.println("String '" + next + "' not found!");
                    }
                } 
            }

            else if (s.equals("add"))
            {
                try
                {
                    next = sc.next();
                    Q.enqueue(next);

                } catch (QueueException e)
                {
                    System.out.println("Unexpected end of input.");
                    
                } catch (Exception e) 
                {
                    System.out.println("Unexpected end of input.");
                    System.exit(0);
                }
            }
            else if (s.equals("dump"))
            {
                try 
                {
                    System.out.println(Q.dump());

                } catch (QueueException e) {}
            }
            else
            {
                if (verbose)
                {
                    System.out.println("Unknown command '" + s + "'.");
                }
            }

        } while(true);
        
    }

}