package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.LoginController;
import controller.SqlConnection;

public class AllQueries 
{
	int userID=LoginController.obj.getUserId();
	
	public static List<Post> getAllPost() throws SQLException 
	{
		int DeptId=18;
		List<Post> list = new ArrayList<>();
		Connection con = SqlConnection.getConnection();
		Statement statement = null;
		PreparedStatement preparedStatement;
		ResultSet result = null;
		try 
		{
			String query="SELECT DeptId FROM tbl_employee e where e.EmpID=?";
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, LoginController.obj.getUserId());
			ResultSet result1=preparedStatement.executeQuery();
			if(result1.next())
			{
				DeptId=result1.getInt("DeptId");
			}
			statement = con.createStatement();
			result = statement.executeQuery("select * from tbl_post");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		while(result.next()) {
			String postDesc = result.getString("PostDesc");
			String postType = result.getString("PostType");
			Date postDate = result.getDate("PostDate");
			int empID = result.getInt("EmpID");
			int postId = result.getInt("PostId");
			int flag = result.getInt("Flag");
			list.add(new Post(postDesc,postType, postDate, empID, postId,flag));
		}
		Post temp = new Post(null,null, null, 0, 0,0);
		list.add(0,temp);
		return list;
	}

	public static void setPostToTable(Post post) throws SQLException {
		Connection con = SqlConnection.getConnection();
		Statement statement = null;
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pStatement = con.prepareStatement("insert into tbl_post (PostId,PostDesc,PostType,PostDate,EmpId,DeptId) value(?,?,?,?,?,?)");
		pStatement.setInt(1, post.getPostId());
		pStatement.setString(2, post.getPostDesc());
		pStatement.setInt(3, Integer.valueOf(post.getPostType()));
		pStatement.setDate(4,new java.sql.Date(post.getPostDate().getTime()));
		pStatement.setInt(5, post.getEmpID());
		System.out.println("check.."+post.getDeptID());
		pStatement.setInt(6, post.getDeptID());
		pStatement.executeUpdate();
		

	}

	public static List<ReplyObject> getReplys(int id) throws SQLException {
		List<ReplyObject> list = new ArrayList<>();
		Connection con = SqlConnection.getConnection();
//		System.out.println(id);
		PreparedStatement pStatement = con.prepareStatement("select * from tbl_reply where PostId = ?");
		pStatement.setInt(1, id);
		ResultSet result = pStatement.executeQuery();
		while(result.next()) {
//			System.out.println("hello");
			int replyId = result.getInt("ReplyId");
			int postId = result.getInt("PostId");
			int empId = result.getInt("EmpId");
			String replyName = result.getString("ReplyName");
			Date replyDate= result.getDate("ReplyDate");
			ReplyObject reply = new ReplyObject(replyId, postId, empId, replyName, replyDate);
			list.add(reply);
			
		}
		list.add(0,null);
		return list;
	}

	public static String getEmpName(int empId) throws SQLException {
		String name = null ;
		Connection con = SqlConnection.getConnection();
		PreparedStatement pStatement = con.prepareStatement("select EmpName from tbl_employee where EmpId = ?");
		pStatement.setInt(1, empId);
		ResultSet result = pStatement.executeQuery();
		if(result.next()) {
			name = result.getString("EmpName");
		}
		return name;
	}

	public static void addReply(String replyData, int postId, int empId) throws SQLException {
		Connection con = SqlConnection.getConnection();
		PreparedStatement pStatement = con.prepareStatement("insert into tbl_reply (ReplyName,ReplyDate,PostId,EmpID) value(?,current_date(),?,?)");
		pStatement.setString(1, replyData);
		pStatement.setInt(2, postId);
		pStatement.setInt(3,empId);

		pStatement.executeUpdate();
	}

	public static void updatePost(String update, int postId) throws SQLException {
		Connection con = SqlConnection.getConnection();
		PreparedStatement pStatement = con.prepareStatement("UPDATE tbl_post SET PostDesc = ? WHERE PostId = ?");
		pStatement.setString(1, update);
		pStatement.setInt(2, postId);

		pStatement.executeUpdate();
	}

	public static void deletePost(int id) throws SQLException {
		Connection con = SqlConnection.getConnection();
		PreparedStatement pStatement = con.prepareStatement("delete from tbl_post where PostId = ?");
		pStatement.setInt(1, id);

		pStatement.executeUpdate();
	}

	public static void setFlag(int id) throws SQLException {
		Connection con = SqlConnection.getConnection();
		PreparedStatement pStatement = con.prepareStatement("update tbl_post set Flag = 1 where PostId = ?");
		pStatement.setInt(1, id);

		pStatement.executeUpdate();
	}

	public static void setPostTODepartment(PostDepartment deptobj)
	{
		Connection con=SqlConnection.getConnection();
		String query="insert into tbl_post_department(PostId,DeptId) values (?,?)";
		
		try {
			PreparedStatement PreparedStatement=con.prepareStatement(query);
			PreparedStatement.setInt(1, deptobj.getPostId());
			PreparedStatement.setInt(2, deptobj.getDeptId());
			PreparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
