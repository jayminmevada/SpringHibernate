package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SqlConnection;

public class ConnectionCheck 
{

	public static void main(String[] args)
	{
		Connection connection;
		PreparedStatement preparedStatement;
		
		connection=SqlConnection.getConnection();
		String query="select * from tbl_department";
		try {
			preparedStatement=connection.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt("DeptId"));
				System.out.println(rs.getString("DeptName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
