
package main;

import java.math.BigInteger;

public class Calculations
{
	
	static int SieveOfAtkin(int limit) 
    { 
		DBConect connection = new DBConect();
		
        boolean isPrimary[] = new boolean[limit]; 
  
        for (int i = 0; i < limit; i++) isPrimary[i] = false;
     
  
        for (int x = 1; x * x < limit; x++)
        { 
            for (int y = 1; y * y < limit; y++)
            { 
   
                int n = (4 * x * x) + (y * y); 
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) isPrimary[n] = true; 
  
                n = (3 * x * x) + (y * y); 
                if (n <= limit && n % 12 == 7)  isPrimary[n] = true; 
  
                n = (3 * x * x) - (y * y); 
                if (x > y && n <= limit && n % 12 == 11) isPrimary[n] = true; 
            } 
        } 
  
        for (int r = 5; r * r < limit; r++)
        { 
            if (isPrimary[r])
            { 
                for (int i = r * r; i < limit; i += r * r) isPrimary[i] = false; 
            } 
        } 
        
        isPrimary[3] = true;
        
        int ID = (Integer.parseInt(connection.getLastID()) + 1);
        int lastNumber = Integer.parseInt(connection.getLastPrimaryNumber());
  
        for (int a = 3; a < limit; a++) 
            {
        		if (isPrimary[a]) 
        		{
        			System.out.print("\n" + a);
        			
        			if(a > lastNumber)
        			{
        				System.out.print(" (dodano do bazy danych).");
        				connection.insertNextPrimaryNumber(ID, BigInteger.valueOf(a));
            			ID++;
        			}
        		}
            }
               
        return 0; 
    } 

}
