/***************************************************
Filename: SolarSystem.java
Author: MIDN Ian Coffey (m261194)
Use Graphics To Draw Orbits
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import javax.imageio.*;

public class SolarSystem extends JComponent implements Runnable
{ 
    // Private Data Fields
    private JButton start;
    private ArrayList<Planet> planetList;

    // Public Planet Class
    public static class Planet
    {
        // Data Fields
        private Point position;
        private int radius;
        private int orbit;
        private double angle = 0.0;
        private Planet orbitingBody;

        // Public Constructor Point pos = body.position
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
            this.position = new Point(body.position.getX() + body.radius, body.position.getY());

            // Assign Values
            this.radius = radius;
            this.orbit = orbit;
            this.orbitingBody = body;

        }

        // Public Method To See if Planet Is Orbiting Something
        public boolean isOrbiting() { return this.orbitingBody != null; }
    }

    // Public Constructor
    public SolarSystem() 
    {
        // Call Super
        super();

        // Create ArrayList
        this.planetList = new ArrayList<Planet>();

        // Create New Frame
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));

        // Create Start Button
        JButton start = new JButton("start");
        start.setSize(100, 50);
        start.setBounds(415, 0, 75, 25);
        this.add(start);

        // Make Frame Visible
        this.pack();
        this.setVisible(true);
        this.setPreferredSize(new Dimension(500,500));

    }

    // Public Method to Add Planet to Solar System
    public void addPlanet(Planet planet) 
    {
        planetList.add(planet);
    }

    // Overridden Paint Method
    public void paintComponent(Graphics g) 
    {
        // Set Canvas
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Traverse Through ArrayList & Draw Planets & Orbits
        for (int i = 0; i < planetList.size(); i++) 
        {
            // Set Color
            g2.setColor(Color.BLUE);

            // Check If Current Planet is Orbiting Anything
            int x, y, o, r;
            int outerR, innerR, outerX, innerX, outerY, innerY; 

            if (planetList.get(i).isOrbiting()) 
            {
                

            } else {
                
                // Calculate Positions
                r = planetList.get(i).radius;
                o = planetList.get(i).orbit;
                x = planetList.get(i).position.getX();
                y = planetList.get(i).position.getY();
            
                // Draw Planet Object
                g2.fill(new Ellipse2D.Double(x - r, y - r, r * 2, r * 2));    

                // Draw Orbit
                g2.draw(new Ellipse2D.Double(x - o, y - o, o * 2, o * 2));  
            } 
 
        }
    }

    // Run Method
    public void run()
    {
        // Pause & Repaint Canvas
        try{Thread.sleep(20);}
        catch(Exception e) {}
        repaint();

    }
}