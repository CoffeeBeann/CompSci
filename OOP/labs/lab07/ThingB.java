
// Import Libraries
import java.util.*;

public class ThingB extends Thing
{
    // Variable Declarations
    private int timeSinceLast;

    // Constructor Method With Arguments
    public ThingB(int row, int col, char lab) 
    {
       super(row,col,lab);
       this.timeSinceLast = 0;
    }
    
    // Method to randomly change direction every 10 frames
    public void maybeTurn()
    {
        int i = rand.nextInt(3);
        this.timeSinceLast++; 
        if (this.timeSinceLast == 10)
        {
            this.timeSinceLast = 0;
            if (i == 1) { this.rightTurn(); }
            if (i == 2) { this.leftTurn(); }
        }
    }

    // Main Method
    public static void main(String[] args) {}
}