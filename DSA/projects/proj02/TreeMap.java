/****************************************
Filename: TreeMap.java
Author: MIDN 2/C Ian Coffey (m261194)
To Implement an Ordered map with AVL Trees
****************************************/

// Import libraries
import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;

public class TreeMap<K extends Comparable<K>,V>  implements Map<K,V> 
{
    // Private inner Node Class
    private class Node 
    {
        // Node variables
        private K key;
        private V value;
        private int height;
        private Node left;
        private Node right;
        
        // Constructor method w/ arguements
        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

    // Private AVL Tree variables
    private Node root;
    private List<K> list;
    private int size = 0;
    
    // AVL constructor method 
    public TreeMap() { root = null; }
    
    /**
     * Method to return the size of the AVL Tree
     * O(1) runtime
     */
    @Override
    public int size() { return this.size; }

    /**
     * Method to return a List of unique keys with an in order traversal
     * O(n) runtime
     */
    @Override
    public List<K> keys() 
    { 
        // Reset key list
        this.list = new ArrayList<K>();
        inOrder(root);
        return list;
    }

    public void inOrder(Node curr) 
    {
        if (curr != null) 
        {
            inOrder(curr.left);
            this.list.add(curr.key);
            inOrder(curr.right);
        } 
    }

    /**
     * Method to return the balance factor of a Node
     */
    public int balanceFact(Node curr) 
    {
        // Left child with no right child
        if (curr.left != null && curr.right == null)
            return curr.left.height + 1;

        // Right child with no left child
        if (curr.left == null && curr.right != null)
            return -1 - curr.right.height;

        // Both children
        if (curr.left != null && curr.right != null)
            return curr.left.height - curr.right.height;

        // No children (Leaf node)
        else
            return 0;
    }
  
   /**
     * Method to update heights throughout an AVL Tree
     */
    public void updateHeight(Node curr)
    {
        // Left child with no right child
        if (curr.left != null && curr.right == null)
            curr.height = curr.left.height + 1;
      
        // Right child with no left child
        else if (curr.left == null && curr.right != null)
            curr.height = curr.right.height + 1;
        
        // Both children
        else if (curr.left != null && curr.right != null)
            curr.height = Math.max(curr.left.height, curr.right.height) + 1;
        
        // No children (Leaf node)
        else
            curr.height = 0;
    }

    /**
     * Left rotation method
     */
    public Node lRotate(Node oldRoot) 
    {
        Node newRoot = oldRoot.right;
        Node middle = newRoot.left;
        newRoot.left = oldRoot;
        oldRoot.right = middle;

        updateHeight(oldRoot);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Right rotation method
     */
    public Node rRotate(Node oldRoot) 
    {
        Node newRoot = oldRoot.left;
        Node middle = newRoot.right;
        newRoot.right = oldRoot;
        oldRoot.left = middle;
        
        updateHeight(oldRoot);
        updateHeight(newRoot);

        return newRoot;
    } 
    /**
     * Method to rebalance AVL Trees 
     */
    public Node rebalance(Node curr) 
    {
        // If AVL Tree is already balanced
        if (-1 <= balanceFact(curr) && balanceFact(curr) <= 1)
            return curr;

        // Right heavy AVL
        else if (balanceFact(curr) <= -2) 
        {
            // Double rotation case
            if (curr.left != null && balanceFact(curr.left) == 1) 
                curr.left = rRotate(curr.left);

            curr = lRotate(curr);
        }

        // Left heady AVL
        else if (balanceFact(curr) >= 2) 
        {
            // Double rotation case
            if (curr.right != null && balanceFact(curr.right) == -1)
                curr.right = lRotate(curr.right);

            curr = rRotate(curr);
        }
        
        // Update heights
        updateHeight(curr);

        return curr;
    }

    /**
     * Method to retrieve a value given a key from the AVL Map
     * O(logn) runtime
     */
    @Override
    public V get(K key) { return getRecurse(root, key); }

    public V getRecurse(Node curr, K key)
    {
        // Null hit
        if (curr == null)
            return null;

        // Traverse down left or right of tree        
        if (key.compareTo(curr.key) < 0)
            return getRecurse(curr.left, key);
        else if (key.compareTo(curr.key) > 0)
            return getRecurse(curr.right, key);
        
        // Match Found
        else
            return curr.value;
    }

    /**
     * Method to return whether a key exist in the AVL Map
     * O(logn) runtime
     */
    @Override 
    public boolean containsKey(K key) { return containsRecurse(root, key); }

    public boolean containsRecurse(Node curr, K key) 
    {
        // Match not Found
        if (curr == null)
            return false;
        
        // Traverse down left or right of tree        
        if (key.compareTo(curr.key) < 0)
            return containsRecurse(curr.left, key);
        else if (key.compareTo(curr.key) > 0)
            return containsRecurse(curr.right, key);
        
        // Match Found
        else
            return true;
    }

    /**
     * Methods to add a key-value pair to the AVL Tree
     * O(logn) runtime
     */
    @Override
    public void put(K key, V value) { root = putRecurse(root, key, value); this.size++; }

    public Node putRecurse(Node curr, K key, V value) 
    {
        // Adding Node to AVL Tree
        if (curr == null) 
            return new Node(key, value);
        
        // If duplicate is found, overwrite value
        if (key.equals(curr.key)) 
        {
            curr.value = value;
            this.size--; 
            return curr; 
        }

        // Traverse down left side if x < curr
        else if (key.compareTo(curr.key) < 0) 
            curr.left = putRecurse(curr.left, key, value);
        
        // Traverse down right side if x > curr
        else    
            curr.right = putRecurse(curr.right, key, value);
    
        // Update height of Node
        updateHeight(curr);

        // Rebalance Tree 
        curr = rebalance(curr);  
        return curr;
    }
  
    /**
     * Method to remove a key-value pair from an AVL Tree
     * O(logn) runtime
     */
    @Override 
    public void remove(K key) throws UnsupportedOperationException 
    { 
        if (root == null)
            throw new UnsupportedOperationException("AVL Map is empty!");

        root = removeRecurse(root, key);
    }

    public Node removeRecurse(Node curr, K key) throws UnsupportedOperationException { return curr; }   
    
    /**
     * Main Method for Testing
     */
    public static void main(String [] args) 
    {
        TreeMap<Integer, String> tree = new TreeMap<Integer, String>();

        for (int i = 1; i <= 10; i++)
            tree.put(i, "a");

        List<Integer> keylist = tree.keys();
        
        for (int i = 0; i < keylist.size(); i++)
            System.out.print(keylist.get(i) + " ");
    }
    
}





