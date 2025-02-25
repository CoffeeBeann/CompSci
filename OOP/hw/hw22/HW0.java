public class HW0
{
  public static class QThread extends Thread
  {
    Queue Q;
    public QThread(Queue Q) { this.Q = Q; }
    public void run()
    {
      for(int i = 0; i < 1000000; i++)
	Q.enqueue("" + i);
      while(!Q.empty())
	Q.dequeue();
    }
  }
  public static void main(String[] args)
  {
    Queue Q1 = new Queue();
    Queue Q2 = null;
    if (args.length == 0)
      Q2 = new Queue();
    else
      Q2 = Q1;
    Thread t1 = new QThread(Q1);
    Thread t2 = new QThread(Q2);
    t1.start();
    t2.start();
  }
}