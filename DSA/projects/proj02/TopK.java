/********************************************************
Filename: TopK.java
Author: MIDN 2/C Ian Coffey (m261194)
Implement a Heap to Track Top Drug Purchasors by Zip Code
*********************************************************/ 

// Import Libraries
import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalStateException;
import java.lang.Math;                             

// TopK Class
public class TopK<T extends Comparable<T>> 
{
    // Private Variable Declarations
    private List<T> elements = new ArrayList<T>();
    private int k;
    
    // Public TopK Constructor
    public TopK(int k) { this.k = k; }
    
    /**
     * Method to return the size of the heap
     */
    public int size() { return elements.size(); }
    
    /**
     * Method to retreive element at a given index
     */
    public T get(int index) { return elements.get(index); }

    /**
     * Method to Add new Element to the collection 
     * Do not call after getTop() has been called.
     */
    public void add(T element) throws IllegalStateException 
    {
        // Check if TopK was called
        if (elements == null)
            throw new IllegalStateException("TopK already called!");
        
        // Add element to Heap
        elements.add(element);
        bubbleUp();
    }

    /**
     * Method to remove and return the minimum element of the elements ArrayList
     * O(logn) runtime
     */
    public T removeMin() 
    {
          // Save Min element and prepare for bubble down
          T min = elements.get(0);
          elements.set(0, elements.get(elements.size() - 1));
          elements.remove(elements.size() - 1);

          // Bubble down stating at the root (index 0)
          bubbleDown(0);
          return min; 
    }
    
    /**
     * Method to bubble down and array 
     * O(logn) runtime
     */
    public void bubbleDown(int index) 
    {
        // Does curr index exist
        int size = elements.size() - 1;
        if (index < size) 
        {
            // Calculate child indexes
            int leftIndex = (2 * index) + 1;
            int rightIndex = (2 * index) + 2;
            T curr = elements.get(index);

            // Do both children exist
            if (leftIndex <= size && rightIndex <= size) 
            {
                // is either child smaller than curr?
                T left = elements.get(leftIndex);
                T right = elements.get(rightIndex);
                
                if (curr.compareTo(right) > 0 && curr.compareTo(left) <= 0) 
                {
                    // swap with right
                    elements.set(index, right);
                    elements.set(rightIndex, curr);
                    bubbleDown(rightIndex);
                }
                
                else if (curr.compareTo(right) <= 0 && curr.compareTo(left) > 0) 
                {
                    // swap with left
                    elements.set(index, left);
                    elements.set(leftIndex, curr);
                    bubbleDown(leftIndex);
                }
              
                else if (curr.compareTo(right) > 0 && curr.compareTo(left) > 0) 
                {
                    if (left.compareTo(right) > 0) 
                    {
                        // swap with right
                        elements.set(index, right);
                        elements.set(rightIndex, curr);
                        bubbleDown(rightIndex);
                    }
                    else 
                    {
                        // swap with left
                        elements.set(index, left);
                        elements.set(leftIndex, curr);
                        bubbleDown(leftIndex);
                    }
                }
            }

            // Does left child exist & right does not
            else if (leftIndex <= size && rightIndex > size) 
            {
                
                // Swap with left if left < curr
                T left = elements.get(leftIndex);
                
                if (curr.compareTo(left) > 0) 
                {
                    // Swap with left
                    elements.set(index, left);
                    elements.set(leftIndex, curr);
                    bubbleDown(leftIndex);
                }
            }
            
            // Does left child not exist & right does
            else if (leftIndex > size && rightIndex <= size) 
            {
                // Swap with right if right < curr
                T right = elements.get(rightIndex);
                if (curr.compareTo(right) > 0) 
                {
                    // Swap with right
                    elements.set(index, right);
                    elements.set(rightIndex, curr);
                    bubbleDown(rightIndex);
                }
            }
        }
    }

    /**
     * Method to bubble up the Arraylist into a min-heap
     * O(logn) runtime
     */
    public void bubbleUp() 
    {
        // Bubble Up sort Arraylist to get proper Heap
        int parentIndex;
        for (int i = elements.size() - 1; i > 0; i = parentIndex) 
        {
            // Calculate Parent Index
            parentIndex = ((i - 1) / 2);
            T parent = elements.get(parentIndex);
            T curr = elements.get(i);
            
            // Swap array positions if Index < Parent
            if (curr.compareTo(parent) < 0) 
            {
                elements.set(parentIndex, curr);
                elements.set(i, parent);
            }
            
        }
        
        // Delete Min Element if size > k
        if (elements.size() > k)
            removeMin();
    }

    /**
     * Retreive Top K elements from the Heap 
     */
    public List<T> getTop() throws IllegalStateException
    {
        if (elements == null)
            throw new IllegalStateException("TopK already called!");
        
        // Perform k removeMins to sort final result
        List<T> largest = new ArrayList<T>();
        
        // Determine when to stop copying
        int stop;
        if (k < elements.size()) 
            stop = k;
        else
            stop = elements.size();

        for (int i = 0; i < stop; i++) 
        {
            T next = removeMin();
            largest.add(0, next);
        } 

        // Make sure getTop is not called again
        elements = null;
        return largest;
    }
}
