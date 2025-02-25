/***************************************************
Filename: LinkedList.java
Author: MIDN Ian Coffey (m261194)
Create A Linked List data structure class
***************************************************/

// Import libraries
import java.util.NoSuchElementException;

/** Implementation of List<T>
 * 
 * Data structure composed of Linked Lists 
 */
public class LinkedList<T> implements List<T> 
{
    /**
     * Inner private Node class
     * @param data T object data
     * @param next Node object pointing to next element
     */
    private class Node
    {
        // Private variable declaration
        private T data;
        private Node next;

        // Public Node constructor w/ arguments
        public Node(T data, Node next) 
        {
            this.data = data;
            this.next = next;
        }

        // Public Node constructor w/o arguments
        public Node() 
        {
            this.data = null;
            this.next = null;
        }

    }

    // Head Nodes for Linked List
    private Node head;
    private int size;

    // Public constructor method for Linked List
    public LinkedList() 
    {
        head = new Node();
        size = 0;
    }

    /** Helper function to peform recursion to return an element given an index
     * 
     * @param tmp Copy of head Node
     * @param count Count of current index
     * @param index Index that user wants to get
     * @return T object of traversal of the Linked List
     */
    public T getHelper(Node tmp, int count, int index)
    {    
        // Check if the index has been found
        if (count == index) 
            return tmp.data;
        
        // Traverse one more item down the list
        count += 1;
        return getHelper(tmp.next, count, index);
    }

    /** Function to return the element of a Linked List given an index
     * 
     * Performs a quick setup before calling another helper function to do recursion
     * 
     * @param index Index that the user wants to get
     * @return T object corresponding to the element the user wants to get
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException 
    {   
        // Check for index errors
        if (index > size || index < 0) 
            throw new IndexOutOfBoundsException("Invalid Index");

        // Set up variables for recrusive function
        Node tmp = head;
        int count = 0;
        return getHelper(tmp, count, index);
    }

    /** Helper function to peform recursion to set an element given an index
     * 
     * @param tmp Copy of head Node
     * @param count Count of current index
     * @param index Index that user wants to set
     * @param data T data that user wants to set
     */
    public void setHelper(Node tmp, int count, int index, T data) 
    {
        // Case if at element
        if (count == index) 
        {
            head = new Node(data, tmp.next);
            return;
        }

        // Case if List size > 1  
        if (count == index - 1) 
        {
            // Check if two more nodes exist after this one
            if (tmp.next.next == null) 
            {
                tmp.next = new Node(data, null);
                return;
            }

            // Set Nodes to point to new data and skip over old Node
            Node insertNode = new Node(data, tmp.next.next);
            tmp.next = insertNode;
            return;
        }

        // Traverse to the next Node
        count += 1;
        setHelper(tmp.next, count, index, data);
    }

     /** Function to set the element of a Linked List given an index
     * 
     * Performs a quick setup before calling another helper function to do recursion
     * 
     * @param index Index that the user wants to set
     * @param data T object user wants to set in Linked List
     */
    @Override 
    public void set(int index, T data) throws IndexOutOfBoundsException 
    {
        // Check for invalid index
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid Index!");

        // Set up variables for recursive function
        Node tmp = this.head;
        int count = 0;
        setHelper(tmp, count, index, data);
        
    }

    /** Helper function to peform recursion to add an element to a Linked List
     * 
     * @param tmp Copy of head Node
     * @param count Count of current index
     * @param index Index that user wants to get
     */
    public void addHelper(Node tmp, int count, int index, T data) 
    {
        // If count is just at the left of the desired index
        if (count == index - 1) 
        {
            // Stitch new Node into Linked List
            Node insertNode = new Node(data, tmp.next);
            tmp.next = insertNode;
            size++;
            return;
        }

        // Edge case for empty list or size 1 list
        if (count == index) 
        {
            // Case if list was empty before the add
            if (head.data == null) 
            {
                head = new Node(data, null);
                
            // Case if List has an element already here
            } else {
                
                head = new Node(data, tmp);
            }

            size++;
            return;
        }
        
        // Traverse next Node
        count += 1;
        addHelper(tmp.next, count, index, data);
    }

    /** Function to add an element to a Linked List given an index
     * 
     * Performs a quick setup before calling another helper function to do recursion
     * 
     * @param index Index that the users wants to add data at
     * @param data T object that user wants to add
     */
    @Override
    public void add(int index, T data) throws IndexOutOfBoundsException 
    {
        // Check for Invalid Index
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid Index!");

        // Set Up Variables for Recursive Function
        Node tmp = head;
        int count = 0;
        addHelper(tmp, count, index, data);
    }

    /** Function to remove an element to a Linked List given an index
     * 
     * Performs a quick setup before calling another helper function to do recursion
     * 
     * @param index Index that the users wants to remove
     */
    public void removeHelper(Node tmp, int count, int index) 
    {
        // Case if first element is to be removed
        if (count == index) 
        {
            // Case if List size is one
            if (size() == 1) 
                head = new Node();
            else 
                head = tmp.next;
            
            size--;
            return;
        }
        if (count == index - 1) 
        {
            // Case if element to be removed is at the end of the list & if element is between two Nodes
            if (tmp.next.next == null) 
                tmp.next = null; 
            else
                tmp.next = tmp.next.next;

            // Decrement size and return
            size--;
            return;
        }

        // Traverse to next node
        count += 1;
        removeHelper(tmp.next, count, index);
    }

    /** Helper function to peform recursion to remove an element in a Linked List
     * 
     * @param index Index that user wants to remove
     */
    @Override
    public void remove(int index) throws IndexOutOfBoundsException 
    {
        // Check for Invalid Index
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index!");

        // Set Up Variables for Recursive Function
        Node tmp = this.head;
        int count = 0;
        removeHelper(tmp, count, index);

    }

    /** Function to remove all elements matching a given element
     * 
     * Performs a quick setup before calling another helper function to do recursion
     * 
     * @param tmp Copy of head Node
     * @param count Count of current index
     * @param element T object that is to be completely removed from the Linked List
     */
    public void removeAllHelper(Node tmp, int count, T element) 
    {
        // Check if element needs to be removed 
        if (tmp.data.equals(element)) {
            remove(count);
            count--;
        }
        
        // End recursion if end of list is hit
        if (count == size() - 1)
            return;
        
        // Go to next Node
        count += 1;
        removeAllHelper(tmp.next, count, element);
    }

    /** Removes ALL elements matching the given one using .equals().
     *
     * @param element The element that should be removed
     */
    public void removeAll(T element) 
    {
        // Set Up Variables for Recursive Function
        Node tmp = head;
        int count = 0;

        // Check if list is empty before trying call function
        if (size() == 0) 
            return;
        
        // Call Helper Function
        removeAllHelper(tmp, count, element);
    }

    /** Gets the 2nd-to-last element.
     *
     * @return The data in the second-to-last node in the list (if any)
     * @throws NoSuchElementException if the list size is less than 2
     */
    public T penultimateHelper(Node tmp) 
    {
        if (tmp.next.next == null) 
            return tmp.data;
        
        return penultimateHelper(tmp.next);
    }

    /** Gets the 2nd-to-last element.
     *
     * @return The data in the second-to-last node in the list (if any)
     * @throws NoSuchElementException if the list size is less than 2
     */
    public T penultimate() throws NoSuchElementException 
    {
        // Check if Size is less than 2
        if (size < 2)
            throw new NoSuchElementException("List Size Less Than 2");
        
        // Set Up Variables for Recursive Function
        Node tmp = this.head;
        return penultimateHelper(tmp);
        
    }

    /** Public method to return the current size of a Linked List
     * 
     * @return List size
     */
    @Override
    public int size() 
    {
        return this.size;
    }

    /** Helper function for Print method to traverse Linked List
     * 
     * @param tmp Node that points to head of Linked List
     */ 
    public void printHelper(Node tmp) 
    {
        // Check that node is not null
        if (tmp.data != null) 
        {
            System.out.print(tmp.data + " ");
        } 

        // Check if end of list has been reached
        if (tmp.next == null)
            return;
        
        // Traverse to next Node
        printHelper(tmp.next);
    }

    /** 
     * Public Print method to output a Linked List
     */
    public void printList() 
    {
        Node tmp = this.head;
        printHelper(tmp);
    } 
}
