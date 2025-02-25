/***************************************************
Filename: Section.java
Author: MIDN Ian Coffey (m261194)
File for Section Object & Methods
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

// Section Class Object
public class Section 
{
    // Variable Declaration
    private String name;
    private int section;
    private String schedule;
    private String location;

    /**
    *   Public Method to Initialize Section Given Scanner
    */
    public static Section read(Scanner in) 
    {
        // Establish Try-Catch Block for Errors
        // I Looked up Java Oracle Documentation for try-Catch Blocks
        try 
        {
            Section newClass = new Section();
            newClass.name = in.next();
            newClass.section = in.nextInt();
            newClass.schedule = in.next();
            newClass.location = in.next();
            return newClass;
        } 
        catch(NoSuchElementException e) 
        {
            return null;
        }
    }

    /**
    *   Method to Return Class Name
    */ 
    public String getName() 
    {
        return this.name;
    }

    /**
    *   Method to Return Section
    */
    public int getSectionNum() 
    {
        return this.section;
    }

    /**
    * Method to Return Period Schedule of Section
    */
    public String getSchedule() 
    {
        return this.schedule;
    }

    /**
    *   Method to Return Class Location
    */
    public String getLocation() 
    {
        return this.location;
    }

    /**
    *   To String Method To Output Section Info
    */
    public String toString() 
    {
        return this.name + " " + this.section + " " + this.schedule + " " + this.location;
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

        // Test Read
        Section test = read(in);

        // Test getName
        System.out.println(test.getName());

        // Test getSection
        System.out.println(test.getSectionNum());

        // Test getSchedule
        System.out.println(test.getSchedule());

        // Test getLocation
        System.out.println(test.getLocation());

        // Test toString
        System.out.println(test.toString());
    }
}