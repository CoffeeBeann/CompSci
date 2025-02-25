/***************************************************
Filename: SolarSystem.java
Author: MIDN Ian Coffey (m261194)
Use Graphics To Draw Orbits
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;

public class StartButton extends JButton implements ActionListener
{ 
    // Data Fields
    private SolarThread thread;

    public StartButton(SolarThread thread, String label) 
    {
        super(label);
        setSize(100, 50);
        setBounds(415, 0, 75, 25);
        addActionListener(this);
        this.thread = thread;
    }

    // Overridden Action Method
    public void actionPerformed(ActionEvent e)
    {
        thread.toggle();
    }

}