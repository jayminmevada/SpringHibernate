package Model;


public class Department {
	
	private int DeptId;
	private String DeptName;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String deptName) {
		
		DeptName = deptName;
	}
	public Department(int DeptId,String deptName) {
		this.DeptId=DeptId;
		DeptName = deptName;
	}
	public int getDeptId() {
		return DeptId;
	}
	public void setDeptId(int deptId) {
		DeptId = deptId;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	@Override
	public String toString() {
		return "Department [DeptId=" + DeptId + ", DeptName=" + DeptName + "]";
	}
	
	

}
