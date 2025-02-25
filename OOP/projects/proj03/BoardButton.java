/*************************************************
Filename: BoardButton.java
Author: MIDN Ian Coffey (m261194)
Object & Method Definitions for Board Button
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardButton extends JButton
{
    // Data Fields
    protected Responder res;
    protected boolean paused, started, done;

    // Responder Class for Button
    public static class Responder implements ActionListener 
    {
        // Data Fields of Responder
        private Board board;
        private BoardButton button;

        public Responder(Board board, BoardButton button)
        {
            // Assign Values
            this.board = board;
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) 
        {
            String str = button.getText();
            if (str.equals("start"))
            {
                button.started = true;

                // Set Text to Pause
                button.setText("pause");

                // Toggle All Tiles to On
                button.paused = false;
                board.activateAll(true);

            } else if (str.equals ("resume"))
            {
                // Set Text to Pause
                button.setText("pause");

                // Toggle All Tiles to On
                button.paused = false;
                board.activateAll(true);

            } else if (str.equals("exit")) 
            {
                System.exit(0);

            } else {

                // Set Text to Start
                button.setText("resume");
                button.paused = true;

                // Toggle All Tiles to off
                board.activateAll(false);
            }     
        }
    }

    // Public Constructor Method
    public BoardButton(Board board) 
    {
        // Call Super to Set Label
        super("start");

        // Assign Responder
        this.res = new Responder(board, this);
        this.paused = true;
        this.started = false;
        this.done = false;

        // Instanciate Button
        this.addActionListener(res);
        this.setSize(125, 25);
        this.setLocation(335, 8);
    }

    public boolean isPaused() { return paused; }
    public boolean hasStarted() { return started; }
    public boolean isDone() { return done; }

    // Public Method To Prompt User To Exit Program When Complete
    public void close() 
    {
        this.setText("exit");
        this.done = true;
    }
}
