
package main;

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
  
        for (int a = 5; a < limit; a++) 
            {
        		if (isPrimary[a]) 
        		{
        			System.out.println(a); 
        			connection.insertNextPrimaryNumber(a);
        		}
            }
               
        return 0; 
    } 

}
