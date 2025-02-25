/*************************************************
Filename: Shook.java
Author: MIDN Ian Coffey
Shook File
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.event.*;

public class Shook extends Thread implements ActionListener
{
    private JTextField field;
    private JLabel label;
    private int count;

    public Shook(JTextField field, JLabel label) throws Exception
    { 
        this.field = field;
        this.label = label;
    }

    public void actionPerformed(ActionEvent e)
    {
        try {
        this.count = Integer.parseInt(field.getText());
        Thread t = new ShookTimer(count, label);
        t.start();
        } catch (Exception t) 
        {
            label.setText("ERROR");
        }
    }
}
