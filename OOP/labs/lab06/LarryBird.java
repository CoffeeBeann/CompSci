/*************************************************
Filename: LarryBird.java
Author: MIDN Ian Coffey (261194), MIDN Sarah Geibig (264083)
File to Contain Larrybird Objects and Methods
**************************************************/

// Import Libraries 
import java.util.*;
import java.io.*;
import ic211.*;

public class LarryBird extends TweeterBird
{ 
    // Method to Filter Out A Given Text Among the Tweets
    public LarryBird filterNo(String s) 
    {
        // Variable Declarations
        LarryBird filterList = new LarryBird();
        Iter i = iterator();
        int numTweets = 0;

        // Check If Next Tweet Exist
        while (i.hasNext()) 
        {
            Tweet nextTweet = i.next();
            if (nextTweet.getText().indexOf(s) == -1) 
            {
                // If String is Not Found, Put It in Queue
                filterList.enqueue(nextTweet);
                numTweets++;
            }
        }

        // Out Number of Tweets Found
        System.out.println(numTweets + " tweets");
        return filterList;
    }

    // Method to Find A Given Text Among the Tweets
    public LarryBird filter(String s) 
    {
        // Variable Declaration
        LarryBird filterList = new LarryBird();
        Iter i = iterator();
        int numTweets = 0;

        // Check If Next Tweet Exist
        while (i.hasNext()) 
        {
            Tweet nextTweet = i.next();

            if (nextTweet.getText().indexOf(s) != -1) 
            {
                // If String is Found, Put It in Queue
                filterList.enqueue(nextTweet);
                numTweets++;
            }
        }

        // Out Number of Tweets Found
        System.out.println(numTweets + " tweets");

        return filterList;
    }

    // Method to Return a Copy of a LarryBird
    public LarryBird copy() 
    {
        TweetQueue.Iter i = iterator();
        LarryBird rtn = new LarryBird();

        // Copy Tweet
        while (i.hasNext()) 
        {
            Tweet nextTweet = i.next();
            rtn.enqueue(nextTweet);
        }
    
        return rtn;

    }

    // Method to Get the Length of a LarryBird Queue
    public int getLen() 
    {
        TweetQueue.Iter i = iterator();
        int numTweets = 0;

        while (i.hasNext()) 
        {
            i.next();
            numTweets++;
        }

        return numTweets;
        
    }

    public static void main(String[] args) 
    { 
        // Test Constructor
        LarryBird test = new LarryBird();

        // Scanner Creation
        Scanner sc = null;
        try 
        {
            sc = new Scanner(new FileReader(args[0]));
        } catch(Exception e) 
        {
            System.out.println("usage: java LarryBird <filename>");
            System.exit(1);
        }

        // While Loop to Read In Tweets
        while (sc.hasNext()) 
        {
            test.enqueue(Tweet.read(sc));
        }

        // Test Filter
        LarryBird filtered = test.filter("bowtie");
        filtered.dump();

        // Test Filter!
        LarryBird filteredNot = filtered.filterNo("crabbe");
        filteredNot.dump();
    }
}