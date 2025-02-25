/*************************************************
Filename: Queue.java
Author: MIDN Ian Coffey (m261194)
File To Hold Queue Class Objects & Methods
**************************************************/

// Import Libraries 
import java.util.*;
import java.io.*;

public class Queue
{
    // Node Class Definition
    private static class Node
    {
        // Variable Declaration In Node
        public Section data;
        public Node next;

        // Constructor With Args
        public Node(Section d, Node n) 
        {
            this.data = d;
            this.next = n;
        }
    }

    // Variable Declaration
    public Node head;
    public Node tail;

    // Constructor
    public Queue()
    {
        this.head = null;
        this.tail = null;
    }

    /**
    *   Method that Adds Section S to The Back of The Queue
    */
    public void enqueue(Section s) 
    {
        // Check for Empty List
        if (this.empty())
        { 
            this.head = new Node(s, null); 
            this.tail = this.head;

        } else {
                
            // Traverse to End of List
            Node tmp = new Node(null, this.head);
            while (tmp.next != null) 
            {
                tmp = tmp.next;
            }

            // Insert new Data
            tmp.next = new Node(s, null);
            this.tail= tmp.next;
        }
    }

    /**
    *   Removes & Returns Next Section in Queue
    */
    public Section dequeue() 
    {
        // Grab Section to Return
        Section rtn = this.head.data;

        // Stitch Out First Item
        this.head = this.head.next;

        return rtn;
    }

    /**
    *   Method To Print Out Queue
    */
    public void printQueue() 
    {
        // Make Sure Queue is Not Empty
        if (!this.empty()) 
        {
            // Traverse List & Print Data
            Node tmp = new Node(null, this.head);
            while (tmp.next != null) 
            {
                System.out.println(tmp.next.data.toString());
                tmp = tmp.next;
            }
        }
    }

    /**
    *   Return Length of A Given Queue
    */
    public int getLen() 
    {
        // Check for Empty Queue
        if (!this.empty()) 
        {
            // Go To End of List & Get Length 
            Node tmp = new Node(null, this.head);
            int count = 0;
            while (tmp.next != null) 
            {
                count++;
                tmp = tmp.next;
            }

            return count;

        } else {

            return 0;
        }
    }

    /**
    *   Method to Determine if Queue is Empty
    */
    public boolean empty() 
    {
        return this.head == null;
    }

    /**
    *   Main Method for Testing
    */
    public static void main(String [] args) 
    {   
        // Local Object Declarations
        Queue Q = new Queue();

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

        // Test read
        Section test = Section.read(in);

        // Test Enqueue
        Q.enqueue(test);

        // Test empty() & dequeue
        while (!Q.empty()) 
        {
            System.out.println(Q.dequeue());
        }
    }
}