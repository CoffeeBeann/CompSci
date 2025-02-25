/*************************************************
Filename: Tile.java
Author: MIDN Ian Coffey (m261194)
Object & Methods Definitions for Tile Class 
***USED MOUSELISTENER/JPANEL DOCUMENTATION***
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tile extends JPanel implements MouseListener
{
    // Data Fields
    private boolean shutdown, on;
    private boolean activated;
    private Pos position;
    private Color color;
    private TileListener tl;

    // Public Constructor
    public Tile(Pos tilePos, Color color) 
    {
        // Create Tile
        this.setPreferredSize(new Dimension(100,100));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(color);
        this.color = color;

        // Add Mouse Listener to Tile
        this.addMouseListener(this); 

        // Set Activated State
        this.activated = false;
        this.shutdown = false;
        this.on = false;

        // Assign Position
        this.position = tilePos;
    }

    // Public Method to Return Tile Position
    public Pos getPos() { return this.position; }

    // Public Method To Return if Tile is Activated
    public boolean isActivated() { return this.activated; }

    // Public Boolean Method to Determine if Tile Colors Match
    public boolean matchColors(Tile t) { return t.color.equals(this.color); }

    // Public Method to Determine if Positions Match
    public boolean matchPos(Tile t) { return this.position.equals(t.position); }

    // Tile Listener Constructor for Board
    public void addTileListener(Board board) { this.tl = board; }

    // Public Method to Activate & Shutdown a Tile
    public void toggle(boolean option) { if (option) { on = true; } else { on = false; }}

    // Public Method To Draw Border Around Tile When Activated
    public void activate() 
    {
        if (!shutdown && on) { this.setBorder(BorderFactory.createLineBorder(Color.black, 10)); }
    }

    // Public Method To Deactivate a Tile
    public static void deactivate(Tile t) 
    { 
        if (!t.shutdown && t.on)
        {
            t.activated = false; 
            t.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    // Method to Permanetly Shutdown a Tile
    public void close() 
    {
        if (!shutdown && on) 
        {
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.tl = null;
            this.activated = false;
            this.shutdown = true;
            this.on = false;
        }
    }
    
    // Overidden Methods
    public void mouseClicked(MouseEvent e) 
    {
        // Output Message
        if (!shutdown && on) 
        {
            if (activated) { System.out.println("Tile " + this.position.toString() + " activated"); }
            else { System.out.println("Tile " + this.position.toString() + " deactivated"); }
        }
    }

    public void mousePressed(MouseEvent e) 
    {
        if (!shutdown && on) 
        {
            // Switch Activation State
            if (activated) { activated = false; tl.deactivated(this); }
            else { activated = true; this.activate(); tl.activated(this); }
        }    
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}
    
    public void mouseExited(MouseEvent e) {}
}