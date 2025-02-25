/*************************************************
Filename: FormatException.java
Author: MIDN Ian Coffey (m261194)
Extend Exception to Define Format Error
**************************************************/

public class FormatException extends Exception 
{
    // Variable Declarations
    private String msg;

    // Public Constructor With Message Argument
    public FormatException(String msg) 
    {
        super(msg);
        this.msg = msg;
    }

    // Method To Return Error Message
    public String getErrorMessage() { return msg; }

}