
// Import Libraries
import java.util.*;

public class Thing 
{
    // Variable Declarations
    protected Random rand = new Random(System.currentTimeMillis());
    private int row, col, dir;
    private char lab;

    // Constructor Method With Arguments
    public Thing(int row, int col, char lab) 
    {
        this.row = row;
        this.col = col;
        this.dir = 0;
        this.lab = lab;
    }
    
    // Method to Turn Thing Right
    public void rightTurn()
    { 
        this.dir = (this.dir + 1) % 4; 
    }

    // Method to Turn Thing Left
    public void leftTurn() 
    { 
        this.dir = (this.dir + 3) % 4; 
    }

    // Method to randomly turn either right or left
    public void maybeTurn()
    {
        int i = rand.nextInt(3);

        if (i == 1) { this.rightTurn(); }
        if (i == 2) { this.leftTurn(); }      

    }

    // Method to make thing take a step in given direction
    public void step()
    {
        final int[] dc = {0,1,0,-1}, dr = {-1,0,1,0};
        this.row += dr[this.dir];
        this.col += dc[this.dir];
    }

    // Tostring method to output Thing row, col, and label
    public String toString() 
    {
        return this.row + " " + this.col + " " + this.lab;
    }

    // Main Method
    public static void main(String[] args) {}
}