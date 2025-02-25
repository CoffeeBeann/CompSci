/*************************************************
Filename: TimerListener.java
Author: MIDN Ian Coffey
Thread to Listen for Start & Stop Actions
***USED THREAD DOCUMENTATION FOR INTERUPT AND SUSPEND()***
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.event.*;

public class TimerListener extends Thread implements ActionListener
{
    private JLabel label;
    private Thread tl;
    private BoardButton but;

    public TimerListener(JLabel label, BoardButton but) throws Exception
    { 
        this.label = label;
        this.tl = new Timer(label, but);
        this.but = but;
    }

    public void actionPerformed(ActionEvent e)
    {
        try 
        {
            if (but.hasStarted() && but.isPaused()) 
            {
                tl.resume();
            }
            else if (!but.hasStarted()) 
            {
                tl.start();
            } 
            else if (but.isDone()) 
            {
                tl.suspend();
            }
            else    
                tl.suspend();

        } catch (Exception t) 
        {
            t.printStackTrace();
        }
    }
}
