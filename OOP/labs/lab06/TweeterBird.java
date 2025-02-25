/*************************************************
Filename: TweeterBird.java
Author: MIDN Ian Coffey (261194), MIDN Sarah Geibig (264083)
File For Objects & Methods of TweeterBird Class
**************************************************/

// Import Libraries 
import java.util.*;
import java.io.*;
import ic211.*;

public class TweeterBird extends TweetQueue
{ 
    // Method to Dump All Tweets
    public void dump() 
    {
        Iter i = iterator();
        int numTweets = 0;
        
        while (i.hasNext()) 
        {
            Tweet nextTweet = i.next();
            System.out.println(nextTweet.toString());
            numTweets++;
        }
        System.out.println(numTweets + " tweets");
    }

    public static void main(String[] args) 
    { 
        // Test Constructor
        TweeterBird test = new TweeterBird();

        // Scanner Creation
        Scanner sc = null;
        try 
        {
            sc = new Scanner(new FileReader(args[0]));
        } catch(Exception e) 
        {
            System.out.println("usage: java TweeterBird <filename>");
            System.exit(1);
        }

        // While Loop to Read In Tweets
        while (sc.hasNext()) 
        {
            test.enqueue(Tweet.read(sc));
        }

        test.dump();

    }
}