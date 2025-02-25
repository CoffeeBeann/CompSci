/***************************************************
Filename: Schedule.java
Author: MIDN Ian Coffey (m261194)
File for Schedule Object & Methods
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

// Section Class Object
public class Schedule
{
    // Variable Declaration
    private Section[] schedule;
    private static int placeHolder = 0;

    /**
    *   Schedule Constructor Method (No Args)
    */
    public Schedule() 
    {
        this.schedule = new Section[10];
    }

    /**
    *   Method to Add Section to a Schedule
    */
    public void addSection(Section s) 
    {
        schedule[placeHolder] = s;
        placeHolder++;
    }

    /**
    *   Method to Print Out Schedule 
    */
    public void show() 
    {
        for (int i = 0; i < placeHolder; i++) 
        {
            System.out.println(schedule[i].toString());
        }
    }

    /**
    *   Method to Return a Section Given Spot in Array
    */
    public Section getSection(int i) 
    {
        return schedule[i];
    }

    /**
    *   Main Method for Testing
    */
    public static void main(String [] args) 
    {
        // Scanner Creation
        Scanner in = null;
        try 
        { 
            in = new Scanner(new FileReader(args[0])); 
        } 
        catch(IOException e) 
        { 
            e.printStackTrace(); 
            System.exit(1); 
        }

        // Test Schedule Constructor
        Schedule test = new Schedule();

        // Test addSection
        Section newSect = Section.read(in);

        // Test show()
        test.show();
    }
}