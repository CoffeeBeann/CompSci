/**********************************************************************
Filename: Pharamcies.java
Author: MIDN 2/C Ian Coffey (m261194)
Program to read in ARCOS data and report pills sold by each pharmacy.
*********************************************************************/

// Import Libraries
import java.util.List;
import java.util.Iterator;

// Pharmacy Class
public class Pharmacies 
{ 
    // Main Method
    public static void main(String[] args) 
    {
        // Read Pill name file from command line or console
        String pillsFile = null;
        if (args.length >= 1) { pillsFile = args[0]; }
        if (pillsFile == null) 
            pillsFile = System.console().readLine("pills file: ");

        // Create TSV Reader from pillFile
        TsvReader pillsLines = new TsvReader(pillsFile);
        
        // Debug stuff
        int lineNum = 0;

        // Traverse & Read TSV File to TreeMap
        Map<String, Integer> drugCount = new TreeMap<String, Integer>();
        for (Map<String,String> aLine : pillsLines) 
        {
            // Parse info from TSV file
            String addInfo = aLine.get("BUYER_ADDL_CO_INFO");
            String pharmacyName = aLine.get("BUYER_NAME");
            int pillCount = (int) (Double.parseDouble(aLine.get("DOSAGE_UNIT")));
 
            if (!addInfo.equals("null")) 
                pharmacyName += " " + addInfo;
                        
            // Put unique info into TreeMap
            if (drugCount.containsKey(pharmacyName))
                drugCount.put(pharmacyName, drugCount.get(pharmacyName) + pillCount);
            else
                drugCount.put(pharmacyName, pillCount);
        }
        
        // Convert Key Map to Array for Traversal
        Iterator<String> list = drugCount.keys().iterator();
        
        while (list.hasNext())
        {
            // Get next Key
            String name = list.next();
            int count = drugCount.get(name);
            System.out.format("%8d %s\n", count, name); 
        }
    }
}
