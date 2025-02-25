/*************************************************
Filename: L11Timer.java
Author: MIDN Ian Coffey
Lab 11 File To Run Timer
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class L11Timer
{  
    public static void main(String[] args)
    {
        // Frame Setup
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 75);
        f.setLocation(500, 500);
        f.setTitle("Lab 11 - Timer");

        // Label Setup
        JLabel count = new JLabel(" DONE");
        count.setForeground(Color.red);
        f.add(count, BorderLayout.WEST);

        // TextField Setup
        JTextField text = new JTextField(20);
        f.add(text, BorderLayout.CENTER);

        // Button Setup
        JButton button = new JButton("countdown");
        f.add(button, BorderLayout.EAST);

        try 
        {
            button.addActionListener(new Shook(text, count));

        } catch (Exception e) 
        {
            text.setText("ERROR");
        }

        f.setVisible(true);
    }
}

