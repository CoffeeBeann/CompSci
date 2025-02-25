/*************************************************
Filename: Lab03.java
Author: MIDN Ian Coffey
Lab03 File To Run Point and Box Classes
**************************************************/

// Import Libraries
import java.util.*;
import java.lang.*;

public class Lab03
{
    public static void main (String [] args)
    {
        // Variable Declaration 
        Scanner in = new Scanner(System.in);
        char q = '"';
        String cmd = "";
        double x = 0, y = 0;
        int start = 0;
        double maxX = 0, maxY = 0, minX = 0, minY = 0;
        Box userBox = new Box(new Point(0,0));

        // While Loop To Keep Inputing Command
        while (!cmd.equals("done")) 
        {
            // Get New Command
            cmd = in.next();

            // Determine Command
            if (cmd.equals("add")) 
            {
                // Read In Next Two Numbers
                x = in.nextDouble();
                y = in.nextDouble();
                
                // Check for First Iteration
                if (start == 0) 
                {
                    // Initialize Values
                    minX = x;
                    maxX = x;
                    minY = y;
                    maxY = y;

                    userBox = new Box(new Point(x,y));
                    start++;

                } else {

                    // Check for Box Expansion
                    if (minX > x) { minX = x; }
                    if (minY > y) { minY = y; }
                    if (maxX < x) { maxX = x; }
                    if (maxY < y) { maxY = y; }

                    // Exand Box if Needed
                    userBox.growBy(new Point(x, y));
                }

            } else if (cmd.equals("box")) 
            {
                // Output Dimensions
                System.out.println(userBox.toString());

            } else if (cmd.equals("map")) 
            {
                // Read In Point
                x = in.nextDouble();
                y = in.nextDouble();
                
                Point newP = userBox.mapIntoUnitSquare(new Point(x, y));
                if (newP != null) 
                {
                    System.out.println(newP.getX() + " " + newP.getY());
                }

            } else if (!cmd.equals("done")) 
            {            
                System.out.println("Error! Unknown command " + q + cmd + q + "!"); 
            }
         }          
    }
}