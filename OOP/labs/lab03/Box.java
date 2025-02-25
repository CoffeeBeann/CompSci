/*************************************************
Filename: Box.java
Author: MIDN Ian Coffey
Box File To Contain Methods for Lab03
**************************************************/

// Import Libraries
import java.util.*;
import java.lang.*;
import java.io.*;

public class Box
{
    // Private Variable Declaration
    private Point a;
    private Point b;

    // Constructor For 1 Box
    public Box(Point p)
    {
        this.a = p.clonePoint();
        this.b = p.clonePoint();
    }

    // Constructor For 2 Boxes
    public Box(Point a, Point b)
    {
        this.a = a.clonePoint();
        this.b = b.clonePoint();
    }

    // Method to Expand Box
    public void growBy(Point p)
    {
        // Check If X is Needed to Grow
        if (p.getX() < this.a.getX())
        {
            this.a.updateX(p.getX());

        } else if (p.getX() > this.b.getX())
        {
            this.b.updateX(p.getX());
        }

        // Check if Y is Needed to Grow
        if (p.getY() < this.a.getY())
        {
            this.a.updateY(p.getY());

        } else if (p.getY() > this.b.getY())
        {
            this.b.updateY(p.getY());
        }
    }

    // given point p in the bounding box, return associated
    // point in the unit square (see notes); return null if
    // p is not inside the bounding box.

    // newx = (x - minX)/width and newy = (y - minY)/height,
    // minX is the minimum x-value in the bounding box, minY is the minimum y-value in the bounding box, 
    // and width and height are the bounding box's width and height.
    public Point mapIntoUnitSquare(Point p)
    {
        // Get X and Y Coords
        double x = p.getX();
        double y = p.getY();

        // Determine Min X & Y of Box
        double minX = Math.min(this.a.getX(), this.b.getX());
        double minY = Math.min(this.a.getY(), this.b.getY());

        // Determine Max X & Y of Box
        double maxX = Math.max(this.a.getX(), this.b.getX());
        double maxY = Math.max(this.a.getY(), this.b.getY());

        // Check If Point is in box
        if (p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY) 
        {
            System.out.println("error");
            return null;
        }

        // Determine Width & Height
        double width = Math.abs(this.a.getX() - this.b.getX());
        double height = Math.abs(this.a.getY() - this.b.getY());

        // Calculate New Points
        double newX = (x - minX) / width;
        double newY = (y - minY) / height;

        // Return Point
        return new Point(newX, newY);

    }

    // Method to Return String Representations of Bound Boxes
    public String toString()
    {
        // Determine Lowest & Highest Coord Values
        double lowX = Math.min(this.a.getX(), this.b.getX());
        double lowY = Math.min(this.a.getY(), this.b.getY());
        double highX = Math.max(this.a.getX(), this.b.getX());
        double highY = Math.max(this.a.getY(), this.b.getY());

        // Return Dimensions
        return lowX + " < x < " + highX + ", " + lowY + " < y < " + highY;
    }

    // Main Method for Testing
    public static void main (String [] args)
    {
        // // Test 1 Point Constructor
        // Point p1 = new Point(3,5);
        // Box box1 = new Box(p1);

        // // Test 2 Point Constructor
        // Point p2 = new Point(5,7);
        // Box box2 = new Box(p1, p2);

        // // Test toString Method
        // System.out.println(box1.toString());
        // System.out.println(box2.toString());

        // // Test Grow By (Pls Work) - it worked :)
        // Point testGrow = new Point(10,10);
        // box1.growBy(testGrow);
        // System.out.println(box1.toString());

        // // Test Map
        // Point p3 = new Point(5,7);
        // Point p4 = box1.mapIntoUnitSquare(p3);

        System.out.println("Testing constructor, growBy, mapIntoUnitSquare\n and toString methods of class Box ...");
        String s = "0.25 -5.75   -1.0 2.25   -.5 1.25";
        InputStream sin = new ByteArrayInputStream(s.getBytes());
        Scanner sc = new Scanner(sin);
        Point p = Point.read(sc);
        Point q = Point.read(sc);
        Point r = Point.read(sc);
        Box b = new Box(p);
        b.growBy(q);
        System.out.println(b.mapIntoUnitSquare(r).toString());
        System.out.println(b);

    }
}