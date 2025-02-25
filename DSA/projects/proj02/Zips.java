/*************************************************
Filename: Zips.java
Author: MIDN 2/C Ian Coffey (m261194)
Program to identify which zip codes had the
highest per-capity number of opioid pills sold.
*************************************************/

// Import Libraries
import java.util.Iterator;
import java.util.List;

// Zips Class
public class Zips 
{
    public static void main(String[] args) 
    {
        // Variable Declaration
        String pillsFile, zipsFile, kstring;
        
        // Read arguments from args[] or console
        if (args.length >= 1)
            pillsFile = args[0];
        else
            pillsFile = System.console().readLine("pills file: ");

        if (args.length >= 2)
            zipsFile = args[1];
        else
            zipsFile = System.console().readLine("zips file: ");

        if (args.length >= 3)
            kstring = args[2];
        else
            kstring = System.console().readLine("k: ");
        
        int k = Integer.valueOf(kstring);

        // Create TSV Readers from parsed arguments
        TsvReader pillsLines = new TsvReader(pillsFile);
        TsvReader zipsLines = new TsvReader(zipsFile);

        // Create TreeMaps to Store information from TSV Files
        TreeMap<Integer,Integer> pillMap = new TreeMap<Integer,Integer>(); // Map<zipCode, pillCount>
        TreeMap<Integer,Integer> popMap = new TreeMap<Integer,Integer>(); // Map<zipCode, Population>
        TreeMap<Integer,String> locMap = new TreeMap<Integer,String>(); // Map<zipCode, Location>
                                                                        
        // Traverse zipFile, read, & store information to pillMap
        for (Map<String,String> aLine : pillsLines)
        {
            // Parse zip code and pill count from pillFile TSV line
            int zipCode = Integer.parseInt(aLine.get("BUYER_ZIP"));
            int pillCount = (int) Double.parseDouble(aLine.get("DOSAGE_UNIT"));
            
            // Store Unique Info into TreeMap
            if (pillMap.containsKey(zipCode))
                pillMap.put(zipCode, pillMap.get(zipCode) + pillCount);
            else
                pillMap.put(zipCode, pillCount); 
        }

        // Traverse pillFile, read, & store information to popMap 
        for (Map<String,String> aLine : zipsLines)
        {
            // Parse city, state, and population info from zipFile TSV Line
            String location = aLine.get("city") + ", " + aLine.get("state_id");
            int population = Integer.parseInt(aLine.get("population"));
            int zipCode = Integer.parseInt(aLine.get("zip"));

            // Store unique information into popMap
            if (popMap.containsKey(zipCode))
                popMap.put(zipCode, popMap.get(zipCode) + population);
            else
                popMap.put(zipCode, population);

            // Store unique information into zipMap
            if (!locMap.containsKey(zipCode))
                locMap.put(zipCode, location);
        }
        

        // Convert Key Map to Array for Traversal
        Iterator<Integer> pillList = pillMap.keys().iterator();  
        Iterator<Integer> popList = popMap.keys().iterator();
        Iterator<Integer> locList = locMap.keys().iterator();

        // Push information into TopK structure
        TopK<Double> topRatio = new TopK<>(k);
        TreeMap<Double,Integer> ratioMap = new TreeMap<Double,Integer>();
        while (pillList.hasNext()) 
        {
            // Grab values from TreeMaps
            int zip = pillList.next();
            int pop = popMap.get(zip);
            int pill = pillMap.get(zip);

            // Calculate ratio
            double ratio = (double) pill / (double) pop;

            // Store <ratio, zipCode> map
            ratioMap.put(ratio, zip);
            topRatio.add(ratio);    
        } 

        // Output Top K values
        List<Double> largest = topRatio.getTop();
        for (int i = 0; i < largest.size(); i++) 
        {
            Double next = largest.get(i);
            int zip = ratioMap.get(next);
            String loc = locMap.get(zip);
            System.out.format("%8.2f %s %d\n", next, loc, zip);
        }
    }
}
