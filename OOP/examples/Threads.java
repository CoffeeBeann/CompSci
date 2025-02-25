/***************************************************
Filename: Threads.java
Author: MIDN Ian Coffey (m261194)
Demo Thread Handling
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class Threads 
{
    static class Foo extends Thread 
    {
        public void run() 
        {
            int x = 49;
            System.out.println(x);
        }
    }

    public static void main(String [] args) throws Throwable
    {
        Thread t = new Foo();
        t.start();
        int x = 25;
        System.out.println(x);
    }
}