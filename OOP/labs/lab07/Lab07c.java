// Import Libraries
import java.util.*;

public class Lab07c
{
  	// This class is for linked lists of Thing's
	private static class Node
	{
		public Thing data;
    	public Node next;
  	}

  	public static void main(String[] args)
  	{
    	int N = 200;
    	if (args.length != 0)
			N = Integer.parseInt(args[0]);
    
    	Node L = null;
    	int count = 0;
    	while(true)
    	{
			// every N rounds add another typeA and typeB Thing
			if (count % N == 0)
			{
				// add a typeA thing to the list
				Thing tA = new Thing(45, 50, 'r'); 
				Node nA = new Node(); 
				nA.data = tA;
				nA.next = L;
				L = nA;
				
				// add a typeB thing to the list
				ThingB tB = new ThingB(55, 50, 'b'); 
				Node nB = new Node(); 
				nB.data = tB;
				nB.next = L;
				L = nB;

				// add a typeC thing to the list
				ThingC tC = new ThingC(50, 60, 'g');
				Node nC = new Node();
				nC.data = tC;
				nC.next = L;
				L = nC;
			}

			// print out each thing
			for(Node T = L; T != null; T = T.next)
				System.out.println(T.data.toString());
			
			System.out.println("done");
			System.out.flush();
      
			// move each thing
			for(Node T = L; T != null; T = T.next)
			{
				T.data.maybeTurn();
				T.data.step();
			}      
    		count++;
  		}
	}
}
