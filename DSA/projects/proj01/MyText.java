/***************************************************
Filename: MyText.java
Author: MIDN Ian Coffey (m261194)
Implement Text File for Editor
***************************************************/

/**
 * Resources used:
 * Since chars cannot handle 'null' values, I had to research the closest thing you can 
 * do to have a null pointer in a char, which is to set it to '\0'.
 * 
 * MGSP utilized to understand concept of LL for MyText.java
 */

// Import Exceptions
import java.util.NoSuchElementException;

/** 
 * Linked List Implementation of Text Editor
 */
public class MyText implements Text 
{
    /**
     * Inner private Node class
     * @param data Char data
     * @param next Node object pointing to next element
     * @param prev Node object pointing to the previous element
     */
    private class Node
    {
        // Private variable declaration
        private char data;
        private Node next;
        private Node prev;

        // Public Node constructor w/ arguments
        public Node(char data, Node next, Node prev) 
        {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        // Public Node constructor w/o arguments
        public Node() 
        {
            this.data = '\0';
            this.next = null;
            this.prev = null;
        }

    }

    // Private variable declarations
    private Node head; // Points to start of List
    private Node tail; // Points to end of List
    private Node cursor; // Cursor Node where cursor.next points to object
    private int cursIndex; // Represents Index that cursor is on
    private int size; // Represents size of text (ie 'hello' = 5)

    // Public constructor method with no arguments
    public MyText() 
    {
        // Create & connect Head & Tail Nodes (Cursor = Head)
        tail = new Node('\0', null, null);
        head = new Node('\0', tail, null);
        cursor = new Node('\0', head, null);
        tail.prev = head;
        size = 0;
        cursIndex = 0;
    }
    
    /**
     * Method to return the element the cursor is pointing to in the Linked List
     */
    @Override
    public char get() throws NoSuchElementException 
    {
        if (cursor.next.data == '\0')
            throw new NoSuchElementException("Invalid index!");
        
        return cursor.next.data;
    }
    
    /**
     * Method to insert an element into a Text object
     */
    @Override
    public void insert(char c)
    {
        /** Insert with empty element
         * 
         * [ ]         or       [Hello ]
         *  ^                         ^
         * 
         * text.insert('x');
         */
        if (cursor.next.data == '\0')
        {
            cursor.next.data = c;

            // Append Tail if needed
            if (cursor.next.next == null) 
            {
                cursor.next.next = new Node('\0', null, cursor.next);
                tail = cursor.next.next;
            }

            cursor.next = cursor.next.next;
            cursIndex++;
            size++;
            return;
        }

        /** Adding at the head of a List
         * 
         * [abc]        or        [a]
         *  ^                       ^
         *  
         * text.insert('x');
         */ 
        if (cursor.next.prev == null) 
        {
            Node newData = new Node(c, cursor.next, null);
            cursor.next.prev = newData;
            head = newData;
            cursIndex++;
            size++;
            return;
        }

        /** Insert with 2 Nodes around element
         * 
         *  [abc]        or        [ab]
         *    ^                      ^
         * 
         * text.insert('x');
         */
        Node newData = new Node(c, cursor.next, cursor.next.prev);
        cursor.next.prev.next = newData;
        cursor.next.prev = newData;
        cursIndex++;
        size++;
        return; 
    }
  
    /**
     * Method to delete the element that the cursor points at
     */
    @Override
    public void delete() throws NoSuchElementException
    {
        if (cursor.next.data == '\0')
            throw new NoSuchElementException("Invalid index!");

        // Check if cursor is at the front of the list
        if (cursor.next == head) 
        {
            // Edge case if size is 1
            if (cursor.next.next.data == '\0') 
            {  
                cursor.next.data = '\0';
            }
            else { // if size > 1
                cursor.next = cursor.next.next;
                head = head.next;
                cursor.next.prev.next = null;
                head.prev = null;
            } 

            size--;
            return;

        }

        // Case for everything else
        cursor.next = cursor.next.next;
        cursor.next.prev.prev.next = cursor.next;
        cursor.next.prev = cursor.next.prev.prev;
        size--;
        return;
    }

    @Override
    public boolean canMoveLeft() { return cursIndex != 0; }

    @Override
    public void moveLeft() throws NoSuchElementException
    {
        if (!canMoveLeft())
            throw new NoSuchElementException("Cannot move mouse left!");
        
        cursor.next = cursor.next.prev;
        cursIndex--;
    }

    @Override
    public boolean canMoveRight() { return cursIndex < size; }

    @Override
    public void moveRight() throws NoSuchElementException
    {
        if (!canMoveRight())
            throw new NoSuchElementException("Cannot move mouse left!");
        
        cursor.next = cursor.next.next;
        cursIndex++;
    }

    @Override
    public void print()
    {
        printHelper(head, cursor);
    }
    
    /**
     * Helper method to print out the contents of a Text object in order using
     * for loops
     */
    private void printHelper(Node head, Node cursor) 
    {
        // Output data
        for (Node tmp = head; tmp.next != null; tmp = tmp.next) 
        {
            if (tmp.data != '\0')
                System.out.print(tmp.data);
        }

        System.out.println();
        
        // Output Cursor
        for (int i = 0; i < cursIndex; i++) 
            System.out.print(" ");
        
        System.out.println("^");
    }
}
