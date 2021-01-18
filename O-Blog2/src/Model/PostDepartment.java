package Model;

public class PostDepartment {

	private int PostId;
	private int DeptId;
	
	
	public PostDepartment() {
		super();
	}
	public PostDepartment(int postId, int deptId) {
		super();
		PostId = postId;
		DeptId = deptId;
	}
	public int getPostId() {
		return PostId;
	}
	public int getDeptId() {
		return DeptId;
	}
	
	
}
