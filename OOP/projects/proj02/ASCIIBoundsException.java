/*************************************************
Filename: ASCIIBoundsException.java
Author: MIDN Ian Coffey (m261194)
Extend Runtime Exception to Define Out of Bounds Error
**************************************************/

public class ASCIIBoundsException extends RuntimeException 
{
    // Local Variable Declaration 
    private String msg; 
    private char badChar;

    // Public Constructor With Only Error Message
    public ASCIIBoundsException(String msg) 
    {
        super(msg);
        this.msg = msg;
        this.badChar = 'X';
    }

    // Public Constructor With Two Args
    public ASCIIBoundsException(String msg, char badChar) 
    {
        super(msg);
        this.msg = msg;
        this.badChar = badChar;
    }

    // Method to Return Error Message To User
    public String getErrorMessage() { return msg; }

    // Method to Return Char That Caused Error
    public char getBadChar() { return badChar; }
}