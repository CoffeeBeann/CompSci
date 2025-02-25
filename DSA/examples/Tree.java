/***************************************************
Filename: Tree.java
Author: MIDN Ian Coffey (m261194)
Demo Tree Data Structure
***************************************************/

// Import libraries
import java.util.*;

// Public Tree class
public class Tree<T> 
{
    // Private inner class Node
    private class Node
    {
        // Private variables
        private T data;
        private Node left;
        private Node right;

        // Public constructor w/o arguments
        public Node() 
        {
            data = null;
            left = null;
            right = null;
        }

        // Public constructor w/ arguments
        public Node(T data, Node left, Node right) 
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Private root Node
    private Node root;

    // Public Tree constructor w/o arguments
    public Tree() 
    {
        root = null;
    }

    // Public Tree constructor w/ arguments
    public Tree(Node root) 
    {
        this.root = root;
    }

    /** Function to perform recursion to find the height of the tree
     * 
     * This is called by the original height method
     * 
     * @param curr Node that refers to the root of the tree
     * @return height as an integer to be added up recrusively to total height
     */
    public int heightHelper(Node curr) 
    {
        // If tree is empty
        if (curr == null)
            return -1;
        
        int rh = 0;
        int lh = 0;

        // Recurse down left side if it exist
        if (curr.left != null)
            lh = heightHelper(curr.left);
        
        // Recurse down right side if it exist
        if (curr.right != null)
            rh = heightHelper(curr.right);

        // Return height of tree + 1 to account for root
        return Math.max(lh,rh) + 1;
    }

    /** Helper function to start the first iteration of recursion to find height
     *  
     * Method to return the height of a tree
     * 
     * @return height of tree as an integer
     */
    public int height() 
    {
        return heightHelper(root);
    }
  
    
    /** Function to perform recursion to traverse the lenght of a tree
     *
     * This function is called by the original function
     *
     * @param curr Node that refers to head of tree
     */
    public void traversalHelper(Node curr) 
    {
        if (curr == null)
          return;

        System.out.print(curr.data + " ");

        traversal(curr.left);
        traversal(curr.right);

    }

    /** Helper function to start the first iteration of the recursion to traverse
     * 
     * Method to traverse a tree
     *
     */
    public void traverse() 
    {
        traversalHelper(this.root);
    }

    /** Function to perform level order traversal recrusion
     *
     * This function is called by a setup function
     *
     * @param curr Node that refers to root of tree
     */
    public void levelTraverse(Node curr) 
    {
      
    }
}
