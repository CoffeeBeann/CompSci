/***************************************************
Filename: Week.java
Author: MIDN Ian Coffey (m261194)
File for Week Object & Methods
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;
import ic211.*;

// Section Class Object
public class Week extends Schedule
{
    // Variable Declaration
    private char[][] grid;

    /**
    *   Public Constructor Method (No Args)
    */
    public Week() 
    {
        // Call Super To Initialize Schedule
        super();
        this.grid = new char[6][5];

        // Instanciate Week to Spaces
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                this.grid[i][j] = ' ';
            }
        }
    }

    /**
    *   Method to Output Schedule In Grid Form
    */
    public void showWeek() 
    {
        // Output Header
        System.out.println("  M T W R F");

        // Print Out Week
        for (int i = 0; i < 6; i++) 
        {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 5; j++) 
            {
                // Check if Space is Needed at End of Char
                if (j == 4) 
                {
                    System.out.println(grid[i][j]);

                } else 
                {
                    System.out.print(grid[i][j] + " ");
                }
            }
        }

    }

    /**
    *   Method to Update Week Given Date & Period
    */
    public void updateWeek(String classTime) 
    {
        // Save First Character From Time
        // Used charAt Documentation
        char date = classTime.charAt(0);
        int period = classTime.charAt(1) - 49;

        // Convert Date to Column
        // I had to Search Switch Syntax Documentation
        int col = 10;
        switch (date)
        {   
            case 'M':
                col = 0;
                break;
            case 'T':
                col = 1;
                break;
            case 'W':
                col = 2;
                break;
            case 'R':
                col = 3;
                break;
            case 'F':
                col = 4;
                break;
            default:
                System.out.println("ERROR");                
                break;
        }

        // Set Period in Schedule
        grid[period][col] = 'x';

    }

    /**
    *   Main Method for Testing
    */
    public static void main(String [] args) 
    {
        // Variable & Object Declarations
        Week newWeek = new Week();
        Scanner in = new Scanner(System.in);
        String[] explodedWeek;
        String cmd = "";

        // Put User in Loop To Enter New Class Times
        while (!cmd.equals("quit")) 
        {
            // Output Week Schedule
            newWeek.showWeek();

            // Read In New Class Time
            cmd = in.next();

            // Explode Schedule
            explodedWeek = DrBrown.explode(cmd);

            for (int i = 0; i < explodedWeek.length; i++) 
            {
                newWeek.updateWeek(explodedWeek[i]);   
            }
        }
    }
}