/*************************************************
Filename: Node.java
Author: MIDN Ian Coffey
File To Contain Methods for Node Class
**************************************************/

// Import Classes
import java.util.*;

public class Node
{
    // Variable Declarations
    public Section data;
    public Node next;

    // Node Constructorl
    public Node(Section d, Node n) 
    {
        data = d;
        next = n;
    }
}