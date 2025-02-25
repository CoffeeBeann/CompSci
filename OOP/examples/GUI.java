/***************************************************
Filename: GUI.java
Author: MIDN Ian Coffey (m261194)
Demo GUIs
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI 
{
    // Action Class
    public static class Responder implements ActionListener 
    {
        JLabel lab;
        JTextField txt;

        public Responder(JLabel lab, JTextField txt) 
        {
            this.lab = lab;
            this.txt = txt;
        }

        public void actionPerformed(ActionEvent e) 
        {
            String msg = txt.getText();
            if(!msg.equals("")) 
            {
                lab.setText(msg);
                txt.setText("");
                txt.grabFocus();
            }

            System.out.println("button was pressed");
        }
    }

    public static void main(String [] args)
    {
        // Setup Basic Frame
        JFrame f= new JFrame();
        f.setTitle("IC211 Frame"); // Title
        f.setSize(1000,400); // Size
        f.setLocation(100,100); // Location

        // Create label
        JLabel lab = new JLabel("hello");

        // Create Button
        JButton but = new JButton("change");

        // Create TextField
        JTextField txt = new JTextField(20);

        // Create Responder
        Responder res = new Responder(lab, txt);
        but.addActionListener(res); // link to button

        f.add(txt, BorderLayout.SOUTH); // put text on south of screen
        f.add(but, BorderLayout.WEST); // put button on west of screen
        f.add(lab, BorderLayout.EAST); // Put label on east of screen

        // Set Screen Visible
        f.pack();
        f.setVisible(true); 
        
    }
}