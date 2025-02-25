/***************************************************
Filename: BSTMap.java
Author: MIDN Ian Coffey (m261194)
Implement a Basic BSTMap for Key Value Pairs
***************************************************/

// Import Libraries
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * BST Implementation of Key-Value pairs class
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> 
{
    // Inner Node class
    private class Node 
    {
        // Private Node variables
        private V data;
        private K key;
        private Node left;
        private Node right;

        // Public Node constructor
        public Node() 
        {
            this.data = null;
            this.key = null;
            this.left = null;
            this.right = null;
        }

        // Public Node constructor with arguments
        public Node(V data, K key, Node left, Node right) 
        {
            // Variable assignments
            this.data = data;
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
        
    // Private variable declarations
    private Node root;
    private int size;

    // Public Constructor of BSTMap
    public BSTMap() 
    {
        root = null;
        size = 0;
    }

    /**
     * Method to inititate recursion to find a given key-value pair
     * 
     * @param key K object user is searching for in tree
     * @return V data associated with K if object is found in BST
     */
    @Override
    public V get(K key) { return getHelper(root, key); }

    /**
     * Method to perform recursion to find a key-value pair
     * 
     * @param curr Node that refers to the root of the BST to start
     * @param key K object that the user is looking for
     * @return V object associated with K
     */
    public V getHelper(Node curr, K key) 
    {
        if (curr == null) 
            return null; // Nothing was Found
        
        // Check if Current Node is less than Key
        if (curr.key.compareTo(key) < 0)
            return getHelper(curr.left, key); // Traverse Left

        // Check if Current Node is greater than Key
        if (curr.key.compareTo(key) > 0)
            return getHelper(curr.right, key); // Traverse Right
       
        // Match Found 
        return curr.data;
    }

    /**
     * Wrapper boolean method to return T/F as to whether a Key exist in a BST
     * 
     * @param key K object to look for
     * @return boolean T/F value
     */
    @Override
    public boolean containsKey(K key) { return containsHelper(root, key); }
    
    /**
     * Method to perform recursive work to check if a key exist in a BST
     * 
     * @param curr Node that refers to the root of the BST to start
     * @return boolean value indicating if the value was found
     */
    public boolean containsHelper(Node curr, K key) 
    {
        // If end of a brach has been reached
        if (curr == null)
            return false;

        // Check if Current Node is less than Key
        if (curr.key.compareTo(key) < 0)
            return containsHelper(curr.left, key); // Traverse Left

        // Check if Current Node is greater than Key
        if (curr.key.compareTo(key) > 0)
            return containsHelper(curr.right, key); // Traverse Right
       
        // Match Found 
        return true;
    }

    /**
     * Wrapper method to initiate the insertion of a Key-value pair in a BST
     * 
     * @param key K key data that is being inserted
     * @param value C object that key references
     */
    @Override
    public void put(K key, V value) { this.root = putHelper(root, key, value); }

    /**
     * Method to perform recursion to insert a Pair into a BST
     * 
     * @param curr Node refering to the root of BST at start
     * @param key K object that points to the key
     * @param value V data object that holds the data of that Node
     */
    public Node putHelper(Node curr, K key, V value) 
    {
        // Add new Node is end is Reached
        size++;
        if (curr == null) {
            return new Node(value, key, null, null);
        }
        // Check if Current Node is less than Key
        if (curr.key.compareTo(key) < 0) {
            curr.left = putHelper(curr.left, key, value); // Traverse Left
        }

        // Check if Current Node is greater than Key
        else if (curr.key.compareTo(key) > 0) {
            curr.right = putHelper(curr.right, key, value); // Traverse Right
        }

        else {
            curr.data = value; 
            size--;
        }

        return curr;
    }

    /**
     * Method to return the current size of a BST
     */
    @Override
    public int size() { return size; }

    /**
     * Method to in-order traverse a given BST and return Deque to console
     */
    @Override
    public Deque<K> traverse() 
    { 
        Deque<K> nodeOrder = new ArrayDeque<K>();
        traverseHelp(root, nodeOrder);
        return nodeOrder;
    }

    public void traverseHelp(Node curr, Deque<K> order) 
    {
        if (curr != null){
            traverseHelp(curr.left, order);
            order.addFirst(curr.key);
            traverseHelp(curr.right, order);
        } 
    }


    /**
     * Remove an element of a BST given a key-value pair
     * 
     * @param key K object indicating element to be removed
     */
    @Override
    public void remove(K key) 
    {
        Node tmp = removeHelp(root, key);
        if (tmp != null)
            root = tmp;
        
    }

    public Node removeHelp(Node curr, K key) 
    {
        if (curr == null)
            return null;
        else if (key.compareTo(curr.key) < 0)
            curr.left = removeHelp(curr.left, key);
        else if (key.compareTo(curr.key) > 0)
            curr.right = removeHelp(curr.right, key);
        else 
        { 
            // first check easy cases
            if (curr.left == null) {
                size--;
                return curr.right;
            }
            else if (curr.right == null) {
                size--;
                return curr.left;
                
            }
            else 
            {
                // hard case: cur.left and cur.right are both not null
                // find the successor and remove it
                Node successor = getMin(curr.right);
                curr.key = successor.key;
                curr.data = successor.data;
                curr.right = removeHelp(curr.right, successor.key);
            }
        }
        return curr;
    }

    public Node getMin(Node curr) 
    {
        if (curr.left == null)
            return curr;
        else
            return getMin(curr.left);
    }

    /**
     * Main Method for testing 
     */
    public static void main(String [] args)
    {
        BSTMap<String,Integer> map = new BSTMap<>();
        map.put("croissant", 100);
        System.out.println(map.size());
        map.put("pain chocolat", 50);
        System.out.println(map.size());
        map.put("gateau", 71);
        System.out.println(map.size());
        map.put("profiterole", 200);
        System.out.println(map.size());
        map.remove("pain chocolat");
        map.remove("gateau");
        map.remove("snickerdoodle");
        System.out.println(map.size()); // 2
        System.out.println(map.get("croissant")); //100
        System.out.println(map.get("profiterole")); //200
        System.out.println(map.get("gateau")); //null
        System.out.println(map.containsKey("pain chocolat")); //false
        System.out.println(map.containsKey("croissant")); //true
    }

} 
