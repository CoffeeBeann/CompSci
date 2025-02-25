/*************************************************
Filename: Encryptor.java
Author: MIDN Ian Coffey (m261194)
Interface for Objects that Provide Encryption Functionaility
**************************************************/

public interface Encryptor 
{
	public String getAlgName();
	public void   init(char[] key) throws ASCIIBoundsException;
	public String encrypt(String plain) throws ASCIIBoundsException;
	public String decrypt(String cipher);	
}