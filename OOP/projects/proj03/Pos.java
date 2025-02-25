/*************************************************
Filename: Pos.java
Author: MIDN Ian Coffey (m261194)
Object & Methods Definitions for Pos Class 
**************************************************/

public class Pos
{
    // Data Fields
    private int row, col;

    // Public Constructor
    public Pos(int r, int c) { row = r; col = c; }

    // Public Method to Return Row Value
    public int getRow() { return row; }

    // Public Method to Return Column Value
    public int getCol() { return col; }

    // Public toString Method to Print Coords
    public String toString() { return row + "," + col; }

    // Public Method to Determine if Points Match
    public boolean equals(Pos p) { return row == p.row && col == p.col; }
}