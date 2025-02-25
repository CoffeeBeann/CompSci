/***************************************************
Filename: Point.java
Author: MIDN Ian Coffey (m261194)
Object & Method Definitions for Point Class
**************************************************/

// Import Libraries
import java.util.*;

public class Point
{
    // Data Fields
    private int x, y;

    // Public Constructor, Get, & toString Methods
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String toString() { return this.x + " " + this.y; }
}
