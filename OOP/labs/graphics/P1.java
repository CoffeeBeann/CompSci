/***************************************************
Filename: P1.java
Author: MIDN Ian Coffey (m261194)
Use Graphics To Draw Orbits
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class P1 
{ 
    public static void main(String [] args) 
    {
        // Create New Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create New Solar System
        SolarSystem solarSys = new SolarSystem();        

        // Create New Thread
        SolarThread t = new SolarThread(solarSys);

        // Create New Start Button
        StartButton start = new StartButton(t, "start");
        frame.add(start);

        // Create & Add Sun 
        SolarSystem.Planet sun = new SolarSystem.Planet(new Point(250,250), 10, 100);
        solarSys.addPlanet(sun);

        // Create & Add Earth
        SolarSystem.Planet earth = new SolarSystem.Planet(sun, 10, 20);
        solarSys.addPlanet(earth);

        // Setup Frame
        frame.add(solarSys);
        frame.pack();
        frame.setVisible(true);

        // Start Thread
        t.start();
    }
}