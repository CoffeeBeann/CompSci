/*************************************************
Filename: QueueException.java
Author: MIDN Ian Coffey (261194)
Extend Runtime Decision to QueueException Handler
**************************************************/

public class QueueException extends RuntimeException
{
    public QueueException   (String msg) 
    {
        super(msg);
    }
}
