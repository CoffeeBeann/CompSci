/*************************************************
Filename: CChange.java
Author: MIDN Ian Coffey
Provide Functionality for Color Changing
**************************************************/

// Import Libraries
import javax.swing.*;
import java.awt.*;

public class CChange
{
    public static void changeColor(JLabel label)
    {
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        System.out.print("new color: ");
        String s = System.console().readLine();
        Color c = label.getForeground();
        if (s.equals("red")) c = Color.red;
        else if (s.equals("blue")) c = Color.blue;
        else if (s.equals("green")) c = Color.green;
        else if (s.equals("cyan")) c = Color.cyan;      
        else System.out.println("Unknown color!");
        label.setForeground(c);
    }
}