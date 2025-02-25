/***************************************************
Filename: error.java
Author: MIDN Ian Coffey (m261194)
Demo Error Handling
**************************************************/

// Import Libraries
import java.util.*;
import java.io.*;

public class error 
{
    public static void main(String [] args) throws Throwable
    {
        // Try Catch Block to Demonatrate Throwables
        try {
            if (!args[4].equals("12345")) 
                throw new Throwable();
        }  
    }
}