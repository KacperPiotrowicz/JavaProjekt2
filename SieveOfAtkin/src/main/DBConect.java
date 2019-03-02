package main;

import java.sql.*;

public class DBConect
{
	
	private Connection con;
	private Statement st;

	
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
	
	public void insertNextPrimaryNumber(int x)
	{
		
		try
		{
			String sql = "INSERT INTO pn VALUES(NULL, " + x +")";
			st.executeUpdate(sql);
			
		}
		
		catch (Exception ex)
		{
			System.out.println("Error: " + ex);
		}
		
	}

}
