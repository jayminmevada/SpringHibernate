package Model;

import java.util.Date;

public class Post 
{
	private int flag;
	private int PostId;
	private String PostDesc;
	private String PostType;
	private Date PostDate;
	private int EmpID;
	private int DeptId;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(String postDesc, String postType, Date postDate, int empID,int postId,int flag,int DeptID) {
		super();
		this.flag = flag;
		PostDesc = postDesc;
		PostType = postType;
		PostDate = postDate;
		EmpID = empID;
		PostId = postId;
		this.DeptId=DeptID;
	}

	public Post(String postDesc, String postType, Date postDate, int empID,int postId,int flag) {
		super();
		this.flag = flag;
		PostDesc = postDesc;
		PostType = postType;
		PostDate = postDate;
		EmpID = empID;
		PostId = postId;

	}

	public int getPostId() {
		return PostId;
	}
	public void setPostId(int postId) {
		PostId = postId;
	}
	public String getPostDesc() {
		return PostDesc;
	}
	public void setPostDesc(String postDesc) {
		PostDesc = postDesc;
	}
	public String getPostType() {
		return PostType;
	}
	public void setPostType(String postType) {
		PostType = postType;
	}
	public Date getPostDate() {
		return PostDate;
	}
	public void setPostDate(Date postDate) {
		PostDate = postDate;
	}
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	
	public int getDeptID()
	{
		return DeptId;
	}
	public int getFlag() {
		return flag;
	}
	@Override
	public String toString() {
		return "Post [PostId=" + PostId + ", PostDesc=" + PostDesc + ", PostType=" + PostType + ", PostDate=" + PostDate
				+ ", EmpID=" + EmpID + "]";
	}
	
	
	
}

