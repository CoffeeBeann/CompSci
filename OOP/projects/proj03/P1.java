/*************************************************
Filename: P1.java
Author: MIDN Ian Coffey (m261194)
Implement Tile Class to Make Simple GUI Game
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P1
{
    public static void main(String [] args) 
    {
        // Create Frame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create & Add Labels
        JLabel north = new JLabel("NORTH");
        JLabel south = new JLabel("SOUTH");
        JLabel east = new JLabel("EAST");
        JLabel west = new JLabel("WEST");
        f.add(north, BorderLayout.NORTH);
        f.add(south, BorderLayout.SOUTH);
        f.add(east, BorderLayout.EAST);
        f.add(west, BorderLayout.WEST);

        // Add New Tile
        f.add(new Tile(new Pos(0,0), null));

        // Make Frame Visible
        f.pack();
        f.setVisible(true);

    }
}