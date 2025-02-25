/*************************************************
Filename: AlgorithmException.java
Author: MIDN Ian Coffey (m261194)
Extend Exception to Define Algorithm Error
**************************************************/

public class AlgorithmException extends Exception 
{
    // Variable Declarations
    private String msg;

    // Public Constructor
    public AlgorithmException(String msg) 
    {
        super(msg);
        this.msg = msg;
    }

    // Method to Return Error Message
    public String getErrorMessage() { return msg; }
}