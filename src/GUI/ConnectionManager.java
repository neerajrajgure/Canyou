package GUI;

import java.sql.*;

public class ConnectionManager {
	final static String db_name= "HMS";
	final static String username = "billing";
	final static String password = "hmsbilling";
	final static String hmsDbUrl ="jdbc:mysql://localhost/"+db_name+"?"+ "user=" + username + "&" + "password=" + password;
	static Connection con = null;

	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CafeBill.hmsDbUrl);
		}
		catch(Exception e){e.printStackTrace();}
		return con;
	}
	
	public void closeConnection(Connection con)
	{
		try
		{
			con.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
}

