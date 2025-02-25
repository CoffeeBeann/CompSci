/***************************************************
Filename: Proj01.java
Author: MIDN Ian Coffey (m261194)
Input & Manage a Midshipmen Class Schedule
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;
import ic211.*;

public class Proj01 
{
    /**
    *   Main Method For Proj01
    */
    public static void main(String [] args) 
    {
        // Object Declarations
        Queue classList = new Queue();
        Scanner in = new Scanner(System.in);
        Scanner sc = null;

        // Variable Declarations
        String className = "";
        String cmd = "";
        int listLen = 0;
        int sectionNum = 0;
        int scheduleSize = 0;

        // Read File Name from args
        try 
        { 
            sc = new Scanner(new FileReader(args[0])); 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
            System.exit(1); 
        }

        // Read File Contents Into Queue 
        int length = 0;
        Section newSect = Section.read(sc);
        while (newSect != null) 
        {
            // Update Length & Enqueue Section Into Queue
            length++;
            classList.enqueue(newSect);
            newSect = Section.read(sc);
        }

        // Transfer Queue Into Array
        Section classListArray[] = new Section[length];
        for (int i = 0; i < length; i++) 
        {
            classListArray[i] = classList.dequeue();
        }

        // Create New Schedule
        Schedule userSchedule = new Schedule();

        // Use While Loop to Keep User in Loop Until Quit
        while (!cmd.equals("quit")) 
        {
            // Get Next Command
            System.out.print("> ");
            cmd = in.next();

            // Determine User Input
            if (cmd.equals("sections")) 
            {
                // Read In Section to get
                className = in.next();

                // Traverse Array To Find Match
                for (int i = 0; i < length; i++) 
                {
                    if (className.equals(classListArray[i].getName())) 
                    {
                        System.out.println(classListArray[i].toString());
                    }
                }

            } else if (cmd.equals("add"))
            {
                // Read in Course Name & Numbers
                className = in.next();
                sectionNum = in.nextInt();

                // Traverse List For Matches & Add to Schedule
                for (int i = 0; i < length; i++) 
                {
                    // Check for Match of Name & Section Number
                    if (className.equals(classListArray[i].getName()) && (sectionNum == classListArray[i].getSectionNum()))
                    {
                        // Add Section to Schedule & Break Out of Loop
                        userSchedule.addSection(classListArray[i]);

                        // Increment Schedule Size & break
                        scheduleSize++;
                        break;

                    } else if (i == length - 1) 
                    {
                        // Check if Entire List has Been Searched Through
                        System.out.println("Error! Section not found!");

                    }
                }

            } else if (cmd.equals("show")) 
            {
                // Print Schedule to Screen
                userSchedule.show();

            } else if (cmd.equals("week")) 
            {
                // Declare New Week
                Week userWeek = new Week();

                // Use For Loop To Extract Sections for Schedule
                for (int i = 0; i < scheduleSize; i++) 
                {   
                    // Get Section from Schedule
                    Section pullSect = userSchedule.getSection(i);

                    // Explode Schedule Into Array
                    String[] explodeSect = DrBrown.explode(pullSect.getSchedule());

                    // Update Week
                    for (int j = 0; j < explodeSect.length; j++) 
                    {
                        userWeek.updateWeek(explodeSect[j]);   
                    }
                }

                // Show Week to Screen 
                userWeek.showWeek();

            } 
            else 
            {
                // Check for Bad Syntax & Unknown Commmands
                if (!cmd.equals("quit")) 
                {
                    System.out.println("Unknown command: " + cmd); 
                }
            }
        }
    }   
} 