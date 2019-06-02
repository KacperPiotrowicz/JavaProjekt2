package main;

import java.math.BigInteger;
import java.sql.*;

public class DBConect
{
	
	private Connection con;
	private Statement st;
	private ResultSet rs;

	
	public DBConect()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://serwer1873035.home.pl/29563192_primarynumbers",
					"29563192_primarynumbers", "qazxsw1@#");
			
			st = con.createStatement();
		}
		
		catch (Exception ex)
		{
			System.out.println("Error DBConect: " + ex);
		}
	}
	
	public void insertNextPrimaryNumber(int ID, BigInteger x)
	{
		
		try
		{
			String sql = "INSERT INTO pn VALUES(" + ID + ", " + x.toString() +")";
			st.executeUpdate(sql);
			
		}
		
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
		}
		
	}
	
	public void printSqlFeedback(String query)
	{
		try
		{
			rs = st.executeQuery(query);
			while(rs.next())
			{
				System.out.print("ID: " + rs.getString(1));
				System.out.print("   Liczba: " + rs.getString(2));
				System.out.print("\n");
			}
			
		}
		
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
		}
			
	}
	
	public String getLastPrimaryNumber()
	{
		String result = null;
		
		try
		{
			rs = st.executeQuery("SELECT * FROM `pn` ORDER BY `ID` DESC LIMIT 1");
			while(rs.next())
			{
				result = rs.getString(2);
			}
			
		}
		
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
		}
		
		return result;	
	}
	
	public String getLastID()
	{
		String result = null;
		
		try
		{
			rs = st.executeQuery("SELECT * FROM `pn` ORDER BY `ID` DESC LIMIT 1");
			while(rs.next())
			{
				result = rs.getString(1);
			}
			
		}
		
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
		}
		
		return result;
			
	}

}
