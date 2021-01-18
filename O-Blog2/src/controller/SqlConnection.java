package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection
{
	public static Connection getConnection()
	{
		String url="jdbc:mysql://localhost:3306/o-blog?useSSL=false&amp;serverTimezone=UTC";
		String username="eipl";
		String password="eipl";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("Connection Success");
			return con;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
