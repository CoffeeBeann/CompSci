/***************************************************
Filename: BoundedList.java
Author: MIDN Ian Coffey (m261194) ***RECEIVED HELP FROM 2/C CABALU***
Define a BoundedList Class with a Fixed Capacity
***************************************************/

// BoundedList Class
public class BoundedList<T> implements List<T> 
{    
    // Private Variable Declaration
    private T[] elements;
    private int capacity;
    private int size;

    // Create a new BoundedList with the Given Maximum Capacity
    public BoundedList(int capacity) 
    {
        @SuppressWarnings("unchecked")
        T[] elements = (T[]) new Object[capacity];
        this.elements = elements;

        // Validate Capacity
        if (capacity < 0)   
            throw new UnsupportedOperationException("Negative Capacity!");

        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException 
    {
        // Check for Invalid Index 
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index!");
        
        return elements[index];   
    }

    @Override
    public void set(int index, T data) throws IndexOutOfBoundsException 
    {
        // Check for Invalid Index
        if (index < 0 || index >= size )
            throw new IndexOutOfBoundsException("Invalid Index!");
        
        // Ensure that Capacity has not been reached
        if (size >= capacity)
            throw new IndexOutOfBoundsException("List Capacity has been Reached!");

        // Assign Data
        elements[index] = data;
    }

    @Override
    public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException 
    {
        // Validate That Index does not Leave Inapropriate Indexes Blank
        if (index >= (size + 1))
            throw new IllegalStateException("Adding more than +1 of the current size");
        
        // Validate that Capacity has not been Reached
        if (size >= capacity) 
            throw new IllegalStateException("List Capacity Reached!");
        
        // Shift Elements Over to the Right
        for (int i = size; i > index; i--) 
        {
            elements[i] = elements[i - 1];
        }

        // Increment Size
        elements[index] = data;
        size++;
    }

    @Override
    public void remove(int index) throws IndexOutOfBoundsException 
    {   
        if (index < 0 || index >= size) 
            throw new IndexOutOfBoundsException("Invalid Index!");

        // Shift Elements Over to the Right
        for (int i = index; i < size - 1; i++) 
        {
            elements[i] = elements[i + 1];
        }
        
        // Decrement Size
        size--;
    }

    @Override
    public int size() 
    {
        return size;
    }

    // Public toString method (Ex "[ 1 2 3 4 ]")
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        
        for (int i = 0; i < size(); i++) 
        {
            sb.append(get(i).toString());
            sb.append(' ');
        }
       

        sb.append(']');
        return sb.toString();
    }

    // Main Method for Testing
    @SuppressWarnings("unchecked")
    public static void main(String [] args) 
    {
        // List Declaration (Empty List)
        List<Integer> List = new BoundedList<Integer>(5);
        
    }
}