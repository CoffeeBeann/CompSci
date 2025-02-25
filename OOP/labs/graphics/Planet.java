/***************************************************
Filename: Planet.java
Author: MIDN Ian Coffey (m261194)
Object & Methods for Planet Class
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class Planet
{
    // Data Fields
    private Point position;
    private int radius;
    private int orbit;
    private double angle = 0.0;
    private Planet orbitingBody;

    // Public Constructor 
    public Planet(Point position, int radius, int orbit) 
    {
        // Assign Values
        this.position = position; // Point Position
        this.radius = radius; // Planet Radius
        this.orbit = orbit; // Orbit Radius
        this.orbitingBody = null; // Not Orbiting Anything
    }

    // Public Constructor Dependent on Existing Planet
    public Planet(Planet body, int radius, int orbit) 
    {
        // Get X & Y Coords From Body
        Point pos = body.getPosition();
        this.position = new Point(pos.getX() + body.getRadius(), pos.getY());

        // Assign Values
        this.radius = radius;
        this.orbit = orbit;
        this.orbitingBody = body;

    }

    // Public Method To See if Planet Is Orbiting Something
    public boolean isOrbiting() { return this.orbitingBody != null; }

    // Getter Methods to Return Planet Values
    public int getRadius() { return this.radius; }
    public int getOrbit() { return this.orbit; }
    public double getAngle() { return this.angle; }
    public Point getPosition() { return this.position; }
    public Planet getOrbitBody() { return this.orbitingBody; }
}

