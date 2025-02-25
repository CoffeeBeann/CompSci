/***************************************************
Filename: NRRandom.java
Author: MIDN Ian Coffey (m261194)
Extend Random Object for Non Repeating Integers
**************************************************/

// Import Libraries
import java.util.*;

public class NRRandom extends Random
{
    // Variable Declaration
    private int previousNum;

    public NRRandom(long seed) 
    {
        super(seed);
        previousNum = -1;
    }

    public int nextInt(int n) 
    {
        int nextNum;
        do 
        {
            nextNum = super.nextInt(n);

        } while(nextNum == previousNum);

        previousNum = nextNum;
        return nextNum;
    }
    
    public static void main(String[] args)
    {
        NRRandom rand = new NRRandom(System.currentTimeMillis());
        for(int i = 0; i < 70; i++) 
        {
            System.out.print(1 + rand.nextInt(6));
        }
        System.out.println();

    }
}