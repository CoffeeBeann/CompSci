/*************************************************
Filename: Board.java
Author: MIDN Ian Coffey (m261194)
Object & Methods Definitions for Board Class 
***USED GRIDLAYOUT DOCUMENTATION***
**************************************************/

// Import Libraries
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements TileListener
{
    // Data Fields
    private BoardButton boardButton;
    private Tile[][] board;
    private int[][] colorIDs;
    private int matches = 0;

    // Public Constructor
    public Board(int seed) 
    {
        // Create & Add 36 Panels To Tile[][] & Assign Positions
        this.board = new Tile[6][6];
        this.colorIDs = DrBrownUtil.getRandomColorIdAssignments(seed, 9);
        this.setLayout(new GridLayout(6,6));
        this.setSize(600, 600);

        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 6; j++) 
            {
                // Create New Tile
                this.board[i][j] = new Tile(new Pos(i, j), DrBrownUtil.idToColor(colorIDs[i][j]));
                board[i][j].addTileListener(this);
                this.add(board[i][j]);
            }
        }
    }

    // Public Method To Toggle all Tiles On of Off
    public void activateAll(boolean option) 
    {
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 6; j++) 
            {
                board[i][j].toggle(option);
            }
        }
    }

    // Public Method to add A Button
    public void addButton(BoardButton button) 
    {
        this.boardButton = button;
    }
    
    // Overridden Functions
    public void activated(Tile t) 
    {
        // Traverse Through Board To Find Activated Tiles
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 6; j++) 
            {
                // Check if Another Board is Activated
                if (board[i][j].isActivated() && !board[i][j].matchPos(t)) 
                {
                    // Check if Activated Tiles Match
                    if (board[i][j].matchColors(t)) 
                    {
                        // Close Both Tiles
                        board[i][j].close();
                        t.close();

                        // Increment Match & Close Button & Stop time if Board is Done
                        if (matches++ == 17) { boardButton.close(); }
                        
                    } else {

                        // Deactivate Both Tiles
                        Tile.deactivate(t);
                        Tile.deactivate(board[i][j]);
                    }
                }
            }
        }
    }

    public void deactivated(Tile t) 
    {
        Tile.deactivate(t);
    }
}