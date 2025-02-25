/*************************************************
Filename: Hasher.java
Author: MIDN Ian Coffey (m261194)
Interface for Objects that Provide Hashing Functionaility
**************************************************/

// Import Libraries
import java.util.*;

public interface Hasher 
{
    public String getHashName();
    public void   init(char[] key) throws ASCIIBoundsException;
    public String hash(char[] plain) throws ASCIIBoundsException;
}