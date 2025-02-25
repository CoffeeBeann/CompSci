/*************************************************
Filename: Timer.java
Author: MIDN Ian Coffey
Provide Functionality for Timer
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;

public class Timer extends Thread
{
    private int time;
    private JLabel field;
    private BoardButton but;

    public Timer(JLabel field, BoardButton but) 
    {
        this.time = 0;
        this.field = field;
        this.but = but;
    }

    public void run()
    {
        try 
        {
            // Local Variables
            int minNum, secNum, i = 0;
            String minStr = "", secStr = "";

            while (!but.isDone()) 
            {
                minNum = i / 60;
                secNum = i % 60;

                if (minNum < 10) 
                    minStr = "0" + Integer.toString(minNum);
                else 
                    minStr = Integer.toString(minNum);
                
                if (secNum < 10)
                    secStr = "0" + Integer.toString(secNum);
                else
                    secStr = Integer.toString(secNum);

                field.setText(minStr + ":" + secStr);
                Thread.sleep(1000);
                i++;
            }

        } catch (Exception e) {}
    }
}