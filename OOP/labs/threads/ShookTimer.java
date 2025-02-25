/*************************************************
Filename: ShookTimer.java
Author: MIDN Ian Coffey
Provide Functionality for Timer
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;

public class ShookTimer extends Thread
{
    private int time;
    private JLabel field;

    public ShookTimer(int count, JLabel field) 
    {
        this.time = count;
        this.field = field;
    }

    public void run()
    {
        try 
        {
            for (int i = time; i >= 0; i--) 
            {
                String txt = String.valueOf(i);
                field.setText("    " + txt + "   ");
                Thread.sleep(1000);
            }
        } catch (Exception e) 
        {
            field.setText("Error");
        }

        // Once Done Set Text Back To Done
        field.setText("DONE");
        
    }
}