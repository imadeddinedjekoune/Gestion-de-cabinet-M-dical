package SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{	
	private static Connection c = null;
	public static Connection connect() 
	{
	    if(c == null)
	    {
	    	try 
	    	{
	    		Class.forName("org.sqlite.JDBC");
			} 
	    	catch (ClassNotFoundException e) 
	    	{
				e.printStackTrace();
			}
	    	try {
	    		c = DriverManager.getConnection("jdbc:sqlite:BDtest.db");
	    	} catch (SQLException e) 
	    	{
				e.printStackTrace();
	    	}
	    }
	    return c;
	}
}