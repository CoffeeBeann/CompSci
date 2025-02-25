/***************************************************
Filename: MyBoundedStack.java
Author: MIDN Ian Coffey (m261194)
Implement Text File for Editor
***************************************************/

/**
 * Resources used:
 * Generic arrays are outright not supported in Jave. So I had to find a work around
 * to this since I need to have an unknown data type. So I found online that you can 
 * cast an array of type Object to what T is, which works
 */

// Import Exceptions
import java.util.NoSuchElementException;

/** 
 * Array Implementation of BoundedStack
 */
@SuppressWarnings("unchecked")
public class MyBoundedStack<T> implements BoundedStack<T>
{
    // Private variables
    private T[] array;
    private T[] amortizeTmp; // Extra array for amortization
    private int size;
    private int top;
    private int bottom;
    private int capacity;

    // Public constructor
    public MyBoundedStack(int capacity) 
    {
        this.capacity = capacity;
        size = 0;
        top = -1;
        bottom = 0;
        array = (T[]) new Object[capacity]; // Default Capacity of 4
    }
    
    /**
     * Method to clear the contents of a Bounded Stack, yet keep the capacity
     * the same
     */
    @Override
    public void clear() 
    {
        array = (T[]) new Object[capacity];
        top = -1;
        bottom = 0;
        size = 0;
    }
    
    @Override
    public boolean isEmpty() { return size == 0; }
  
    /**
     * Method to grab the item at the top of the stack, remove, and return it to
     * the user. 
     */
    @Override
    public T pop() throws NoSuchElementException
    {
        if (size == 0)
            throw new NoSuchElementException("Invalid index");
        
        // Grab element to return
        T rtn = array[top];
        array[top] = null;
        size--;

        if (size == 0) // Reset configuration if array is now empty
        {
            top = -1;
            bottom = 0;
        }
        else if (top == 0)
            top = size - 1;
        else
            top--;

        return rtn;
        
    }
    
    /**
     * Method to add an element to a Bounded Stack. 
     * Data will overlap to the beginning of the array if the end of an array is
     * hit
     */
    @Override
    public void push(T item) 
    {
        // Check if capacity has been hit
        if (size == capacity) 
        {
            // Move top
            if (top + 1 == size)
                top = 0;
            else
                top++;
            
            // Assign data
            array[top] = item;
            
            // Move bottom
            if (bottom + 1 == size)
                bottom = 0;
            else   
                bottom++;
            
        }

        // If capacity is not hit, just add to stack
        else if (top == size) 
        {
            top = 0;
            array[top] = item;
            size++;

        } else {

            array[top + 1] = item;
            top++;
            size++;
        }
        
    }

    /**
     * Method to set the capacity of a Bounded Stack to a given value
     * Will delete values if bounded list is shrunk
     */
    @Override
    public void setCapacity(int newCapacity) 
    {
        // Set tmp array to new array of the given capacity
        amortizeTmp = (T[]) new Object[newCapacity];
        int newSize = 0;
        int stopCount; // Keeps track of what to put in new array and in what order

        // reset array if size <= 1
        if (newCapacity <= 1) 
        {
            amortizeTmp = (T[]) new Object[newCapacity];
            if (size != 0)
            {
                amortizeTmp[0] = array[top];
                newSize = 1;
            }
            else
                newSize = 0;
            top = 0;
            bottom = 0;
            
        }
        else if (capacity > newCapacity) // if array size needs to shrink 
        {
            stopCount = capacity - newCapacity;
    
            while (stopCount >= 0)
            {
                amortizeTmp[stopCount] = array[top];
                top--;
                // Make sure top pointer wraps around array
                if (top == -1)
                    top = capacity - 1; 

                newSize++;
                stopCount--;
            }
            
            // Assign new top and bottom values 
            top = capacity - newCapacity;
            bottom = 0;

        }
        else if (capacity < newCapacity) // if array needs to grow
        {
            stopCount = size;
            int i = 0;
            while (stopCount > 0) 
            {
                // assign tmp array values from current array and count up
                amortizeTmp[i] = array[bottom];
                bottom++;
                if (bottom == size)
                    bottom = 0;
                
                newSize++;
                stopCount--;
                i++;
            }

        }
        else
            return;
        
        // Assign new values
        size = newSize;
        capacity = newCapacity;
        array = amortizeTmp;
        
    }
  
    /**
     * Method to print out the contents of a bounded stack in order from bottom
     * to top.
     */
    public void print() 
    {
        if (top < bottom) 
        {
            System.out.print("[ ");
    
            for (int i = bottom; i < size; i++) 
                System.out.print(array[i] + " ");

            for (int j = 0; j <= top; j++) 
                System.out.print(array[j] + " ");
            
            System.out.println("]");

        } else {

            System.out.print("[ ");

            for (int k = 0; k < size; k++) 
                System.out.print(array[k] + " ");
            
            System.out.println("]");
        }
    }

    public static void main(String [] args) 
    {
        BoundedStack<Integer> array = new MyBoundedStack<Integer>(5);

        for (int i = 0; i < 10; i++)
            array.push(i);

        array.setCapacity(0);

        array.print();

    }
}
