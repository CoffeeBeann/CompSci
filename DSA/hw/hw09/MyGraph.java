/****************************************
Filename: MyGraph.java
Author: MIDN Ian Coffey (m261194)
ADT Graph Implementation
****************************************/


/**********************************************
Resources Used...
Geeks for Geeks "Traversal through a Hashmap"
HashMap java documentation
Set java documentation
**********************************************/


// Import Libraries
import java.util.*;

// MyGraph Class
public class MyGraph implements Graph 
{
    // Private Variables
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    /**
     * Method to return a list of all vetices in this graph
     */
    @Override
    public List<String> vertices() 
    {
        List<String> rtn = new ArrayList<>();
        Iterator mapIter = map.keySet().iterator();

        for (String vertice : map.keySet())
            rtn.add(vertice);
        
        return rtn;
    }

    /**
     * Method to return a list of all adjacent edges of a given node
     * @throws NoSuchElementException if source does not exist.
     */
    @Override
    public List<String> neighbors(String source) throws NoSuchElementException
    {
        // Check for No Element Exception
        if (!map.containsKey(source))
            throw new NoSuchElementException();

        // Use Iterator to Traverse through adjacency list
        List<String> edges = map.get(source);

        return edges;
    }

    /** 
     * Method to returns true if an edge from source to dest exists.
     * @throws NoSuchElementException if source or dest nodes do not exist.
     */
    @Override
    public boolean getEdge(String source, String dest) throws NoSuchElementException 
    { 
        // Check if source node exists
        List<String> vertices = vertices();
        if (!vertices.contains(source) || !vertices.contains(dest)) 
            throw new NoSuchElementException();

        // Grab Arraylist and check if dest exists
        List<String> list = map.get(source);

        return list.contains(dest);
    }

    /**
     * Method to add a new vertex to the graph, if it doesn't already exist.
     * No error if a vertex with that name exists already.
     */
    @Override
    public void addVertex(String label) 
    {
        if (!map.containsKey(label))
            map.put(label, new ArrayList<>());
    }

    /** 
     * Method to add or removes an edge from the graph.
     * If weight is true, the edge should be added if it doesn't already exist.
     * If weight is false, the edge should be removed if it does exist.
     * @throws NoSuchElementException if source or dest vertex names don't exist.
     */
    @Override 
    public void putEdge(String source, String dest, boolean weight) throws NoSuchElementException 
    {
        // Check if source and dest exist
        List<String> vertices = vertices();
        if (!vertices.contains(source) || !vertices.contains(dest))
            throw new NoSuchElementException();
        
        // Grab Arraylist mapped to Vertice
        List<String> list = map.get(source);

        // If weight is true and dest does not already exist
        if (weight && !list.contains(dest)) 
            map.get(source).add(dest);

        // If weight is false and dest does exist
        if (!weight && list.contains(dest))
            map.get(source).remove(dest);
    }

    // Main Method for Testing 
    public static void main(String[] args) 
    {
        // Check if graph file was given in args
        String graphFile;
        if (args.length > 0) 
            graphFile = args[0];
        else
            graphFile = Stdin.input("Enter graph file: ");

        // Read graph file
        Graph G = DotReader.readFrom(graphFile);
        Graph A = new MyGraph();

        A.addVertex("a");
        A.addVertex("c");
        A.putEdge("a","c", true);
        A.getEdge("a","b");
        
    }
}
