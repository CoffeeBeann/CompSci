public class Queue
{
  public synchronized void enqueue(String s)
  {
    if (head == null) 
      head = tail = new Node(s,null);
    else 
    { 
      tail.next = new Node(s,null); 
      tail = tail.next; 
    }
  }
  public synchronized String dequeue()
  { 
    Node t = head; 
    head = head.next;
    if (head == null)
      tail = null;
    return t.data; 
  }
  public synchronized boolean empty() { return head == null; }

  private class Node
  {
    public String data;
    public Node next;
    public Node(String d, Node n) { data = d; next = n; }
  }
  private Node head = null, tail = null;
}