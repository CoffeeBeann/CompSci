/***************************************************
Filename: SolarThread.java
Author: MIDN Ian Coffey (m261194)
Use Threads to Run Multiple Components
**************************************************/

// Import Libraries
import java.util.*;

public class SolarThread extends Thread
{
    public SolarThread(SolarSystem sys) 
    {   
        super();
    }

    public void toggle() 
    {
        this.start();
    }
}