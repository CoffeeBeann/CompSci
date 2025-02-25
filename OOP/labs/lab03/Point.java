/*************************************************
Filename: Point.java
Author: MIDN Ian Coffey
Define Variables & Methods for Point Class
**************************************************/

// Import Libraries
import java.util.*;

public class Point
{
    // Private Variable Declaration
    private double x;
    private double y;

    // Constructor
    public Point (double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // Reads an X and Y value to Return Point
    public static Point read(Scanner in)
    {
        double x = in.nextDouble();
        double y = in.nextDouble();
        return new Point(x, y);
    }

    // Returns X and Y Coordinates
    public String toString()
    {
        return this.x + " " + this.y;
    }

    // Method to Clone Points
    public Point clonePoint()
    {
        return new Point(this.x, this.y);
    }

    // Method to Get X Value
    public double getX()
    {
        return this.x;
    }

     // Method to Get Y Value
    public double getY()
    {
        return this.y;
    }

    // Method to Update X value of Point
    public void updateX(double newX)
    {
        this.x = newX;
    }

    // Method to Update Y value of Point
    public void updateY(double newY)
    {
        this.y = newY;
    }


    // Main Method for Testing
    public static void main(String[] args)
    {
        // Test Point Constructor
        Point test = new Point(1,2);

        // Test Read Method
        Scanner in = new Scanner(System.in);
        Point newPoint = read(in);

        // Test Clone Method
        Point clone = newPoint.clonePoint();

        // Test getX and getY
        System.out.println(test.getX() + " " + test.getY());

        // Test toString Method
        System.out.println(test.toString() + " " + newPoint.toString() + " " + clone.toString());

    }
}