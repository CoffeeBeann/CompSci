/****************************************
Filename: CycleFinder.java
Author: MIDN Ian Coffey (m261194)
To Determine if a Node is in a Cycle
****************************************/

// Import Libraries
import java.util.*;

public class CycleFinder 
{
    /**
     * Method to check if a node is in a cycle
     */
    public static boolean hasCycle(Graph graph, String start) 
    {
        // Create sets and stack for visisted and fringe list
        Set<String> visited = new HashSet<>();
        Stack fringe = new Stack();

        // Push start to the stack
        fringe.push(start);
        
        // Traverse through fringe
        while (!fringe.empty()) 
        {
            // Grab next object off stack
            String uu = fringe.pop().toString();

            // Check if we have not visited uu
            if (!visited.contains(uu)) 
            {
                // Add to stack
                visited.add(uu);
                for (String vv : graph.neighbors(uu)) 
                {
                    fringe.push(vv);
                    
                    // Cycle found
                    if (vv.equals(start))
                        return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) 
    {
        // XXX you shouldn't need to change anything here
        String dotf = Stdin.input("graph file: ");
        Graph graph = DotReader.readFrom(dotf);

        while (true) 
        {
            String start = Stdin.input("starting node or 'quit': ");
            if (start == null || start.equals("quit")) break;
            if (hasCycle(graph, start))
                System.out.format("%s is in a cycle.\n", start);
            else
                System.out.format("%s is NOT in a cycle.\n", start);
        }
    }
}
