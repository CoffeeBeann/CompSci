/*************************************************
Filename: Lab1a.java
Author: MIDN Ian Coffey
Square An Inputed Number
**************************************************/

public class Lab1a 
{
    public static void main (String [] args) 
    {
        // Variable Declarations
        int[] x = new int[10];
        int k = 0;

        // While Loop for K
        while(k < 10) 
        {
            x[k] = k*k;
            k++;
        }

        // Loop To Output Square
        for (int j = 0; j < 10; j++) 
        {
            System.out.println(j + " squared is " + x[j]);
        }
    }
}