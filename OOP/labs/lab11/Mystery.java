/*************************************************
Filename: Mystery.java
Author: MIDN Ian Coffey
Mystery File
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.event.*;

public class Mystery implements ActionListener
{
    private JLabel label;

    public Mystery(JLabel label) 
    { 
        this.label = label; 
    }

    public void actionPerformed(ActionEvent e)
    {
        try 
        {
            //Thread t = new Time(null, label);
            //t.start();

        } catch (Exception t) 
        {
            System.out.println("ERROR");
            System.exit(1);
        }
    }
}
