import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HW1Frame extends JFrame
{
    static class WindowDisposer implements WindowListener
    {
        private CBn button;

        public WindowDisposer(CBn button) 
        {
            super();
            this.button = button;
        }

        public void windowActivated(WindowEvent e) { }
        public void windowClosed(WindowEvent e) { }
        public void windowClosing(WindowEvent e)
        { 
            System.out.println("Button clicked " + button.getClicks() + " times."); 
            System.exit(0);
        }
        public void windowDeactivated(WindowEvent e) { }
        public void windowDeiconified(WindowEvent e) { }
        public void windowIconified(WindowEvent e) { }
        public void windowOpened(WindowEvent e) { }
    }

    public HW1Frame()
    {
        CBn b = new CBn("click me");
        add(b,BorderLayout.NORTH);
        WindowDisposer wd = new WindowDisposer(b);
        this.addWindowListener(wd);
        pack();
    }
}