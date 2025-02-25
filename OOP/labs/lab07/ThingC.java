
// Import Libraries
import java.util.*;

public class ThingC extends ThingB
{
    // Variable Declaration
    private int timeSinceDiagonal;
    boolean lastWasLeft = false;

    // Constructor Method With Arguments
    public ThingC(int row, int col, char lab) 
    {
        super(row,col,lab);
        this.timeSinceDiagonal = 0;
    }

    // Method to Determine to Change Direction
    public void maybeTurn()
    {
        // Check if turn needs to be made
        if (timeSinceDiagonal == 20) 
        {
            int nextTurn = rand.nextInt(2);
            if (nextTurn == 0) 
            {
                leftTurn();
            } else {

                rightTurn();
            }

            timeSinceDiagonal = 0;
        }


        // Change direction
        if (lastWasLeft)  {
            rightTurn();
            lastWasLeft = false;
        }
        else {
            leftTurn();
            lastWasLeft = true;
        }

        timeSinceDiagonal++;

    }

    // main Method
    public static void main(String[] args) {}
}