/*************************************************
Filename: P3.java
Author: MIDN Ian Coffey (m261194)
Implement Tile Class to Make Simple GUI Game
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P3
{
    public static void main(String [] args) 
    {
        // Seed Declaration
        int seed;
        try { seed = Integer.parseInt(args[0]); } 
        catch (Exception e) { seed = (new Random()).nextInt(1000); }

        // Create Frame
        Board board = new Board(seed);

    }
}