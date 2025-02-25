/*************************************************
Filename: Queue.java
Author: MIDN Ian Coffey
File To Contain Methods for Queue Class
**************************************************/

// Import Libraries
import java.util.*;

public class Queue
{
    // Variable Declaration
    private Node head;
    private Node tail;

    // Constructor Method
    public Queue() 
    {
        this.head = new Node();
        this.tail = new Node();
    }

    /**
    * Method to adds S to The Back of The Queue
    */
    public void enqueue(String s) 
    {
        Node tmp = new Node(s, this.tail.next);
        while (tmp.next != null) 
        {
        }
    }
    /**
    * Removes & Returns String From Front of Queue
    */
    public String dequeue() 
    {
        return "";
    }

    /**
    * Return True if Queue is Empty
    */
    public boolean empty() 
    {
        return this.head == null;
    }

    // Main Method for Testing
    public static void main (String [] args)
    {
        // Test Empty List
        Queue emptyList = new Queue();
        //System.out.println(emptyList.empty());
    }
}