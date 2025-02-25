/*************************************************
Filename: Lab06.java
Author: MIDN Ian Coffey (261194), MIDN Sarah Geibig (264083)
Sort Through Twitter Data as User Desires
**************************************************/

// Import Libraries 
import java.util.*;
import java.io.*;
import ic211.*;

public class Lab06
{ 
    public static void main(String[] args) 
    { 
        // Variable Declaration
        String cmd = "";
        String filter;

        // Scanner Creation
        Scanner in = new Scanner(System.in);
        Scanner sc = null;
        try 
        {
            sc = new Scanner(new FileReader(args[0]));
        } catch(Exception e) 
        {
            System.out.println("usage: java Lab06 <filename>");
            System.exit(1);
        }

        // Create Tweet Queue
        LarryBird tweets = new LarryBird();
        LarryBird filteredTweets = new LarryBird();

        // While Loop to Read In Tweets
        int numTweets = 0;
        while (sc.hasNext()) 
        {
            tweets.enqueue(Tweet.read(sc));
            numTweets++;
        }

        // Copy tweets to filteredTweets
        filteredTweets = tweets.copy();

        // Output Number of Tweets Found
        System.out.println(numTweets + " tweets");

        // While Loop To Keep User
        while (!cmd.equals("quit")) 
        {
            // Read in Next Command
            System.out.print("> ");
            cmd = in.next();

            if (cmd.equals("dump")) 
            {
                filteredTweets.dump();

            } else if (cmd.equals("filter!")) 
            {
                filter = in.next();
                filteredTweets = filteredTweets.filterNo(filter);

            } else if (cmd.equals("filter")) 
            {
                filter = in.next();
                filteredTweets = filteredTweets.filter(filter);

            } else if (cmd.equals("reset")) 
            {
                filteredTweets = tweets.copy();
                int len = filteredTweets.getLen();
                System.out.println(len + " tweets");
                
            } else 
            {
                if (!cmd.equals("quit")) 
                {
                    System.out.println("Unknown command: " + cmd);
                }
            }
        }

    }
}