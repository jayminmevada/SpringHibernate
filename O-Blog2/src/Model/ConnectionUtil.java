package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

	
	String dbURL = "jdbc:mysql://localhost:3306/o-blog";
	String username = "eipl";
	String password = "eipl";
    static Connection conn;
	
	
	public  void connection() throws SQLException{
		
		 
		try {
		 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
}
