/*************************************************
Filename: DrBrownUtil.java
Author: Prof Brown
Generate Random colorIDs from 0-8 for Tiles
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * DrBrownUtil is a class for generating random assignments
 * of "colorId" values in a 6x6 grid such that each color
 * appears an even number of times. A "colorId" is an int
 * in the range 0,...,8, and you call idToColor(k) to have
 * colorId k mapped to the actual java Color object.
 */ 
public class DrBrownUtil 
{
    // Create Color Array
    private static Color[] colors = {
        new Color(220,20,60),
        new Color(218,112,214),
        new Color(65,105,225),
        new Color(0,245,255),
        new Color(0,201,87),
        new Color(255,255,0),
        new Color(255,140,0),
        new Color(154,255,154),
        new Color(173,166,144)
    };

    /**
     * idToColor(k) returns the Color object id k refers to.
     */
    public static Color idToColor(int k) {
        return k < 0 || colors.length <= k ? Color.WHITE : colors[k];
    }

    /**
     * getRandomColorIdAssignments(seed,numColors) generates a random
     * arrangements of colorIds in a 6x6 grid.  The randomness is 
     * controlled by parameter seed, and the colorIds used are limited
     * to 0,...,numColors-1.
     */
    public static int[][] getRandomColorIdAssignments(int seed, int numColors) 
    {
        Random rand = new Random(seed);
        if (numColors < 0 || numColors > 9)
        numColors = 9;
        Pos[] A = new Pos[36];
        int k = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 6; j++)
                A[k++] = new Pos(i,j);
        
        for(int n = 0; n < 100; n++)
        {
            int i = rand.nextInt(36);
            int j = rand.nextInt(36);
            Pos tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
        int[][] M = new int[6][6];
        for(int i = 0; i < 36; i += 2)
        {
            int c = rand.nextInt(numColors);
            M[A[i].getRow()][A[i].getCol()] = c;
            M[A[i+1].getRow()][A[i+1].getCol()] = c;
        }
        
        return M;
    }
}