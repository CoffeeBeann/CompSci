/*************************************************
Filename: P5.java
Author: MIDN Ian Coffey (m261194)
Implement Tile Class to Make Simple GUI Game
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class P5
{
    public static void main(String [] args) throws Exception
    {
        // Create Frame
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600, 685);
        frame.setTitle("Project 3 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Row Panel
        JPanel row = new JPanel();
        row.setSize(600, 50);
        frame.add(row);

        // Seed Declaration
        int seed;
        try { seed = Integer.parseInt(args[0]); } 
        catch (Exception e) { seed = (new Random()).nextInt(1000); }

        // Add Board to Frame
        Board board = new Board(seed);
        board.setLocation(0, 50);
        frame.add(board);

        // Create Time Label
        JLabel time = new JLabel("00:00");
        time.setFont(new Font("Arial", Font.PLAIN, 22));
        row.add(time);

        // Create Start Button
        BoardButton start = new BoardButton(board);
        start.addActionListener(new TimerListener(time, start));
        board.addButton(start);
        frame.add(start);

        // Make Everything Visible
        frame.setVisible(true);
    }
}