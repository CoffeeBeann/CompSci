/*************************************************
Filename: Time.java
Author: MIDN Ian Coffey
Time File
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.event.*;

public class Time implements ActionListener
{
    private JTextField field;
    private JLabel label;
    private int count;

    public Time(JTextField field, JLabel count) throws Exception
    { 
        this.field = field;
        this.label = count;
    }

    public void actionPerformed(ActionEvent e)
    {
        
        try {
            this.count = Integer.parseInt(field.getText());
            Thread t = new CountTime(count, label);
            t.start();
        } catch(Exception t) 
        {
            label.setText(" ERROR");
        }
    }
}
