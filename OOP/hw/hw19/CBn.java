import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CBn extends JButton implements ActionListener
{
    private int count = 0;

    public CBn(String label)
    { 
        super(label); 
        this.addActionListener(this);
    }

    public int getClicks() { return count; }
    
    public void actionPerformed(ActionEvent e)
    {
        count++;
    }
}
