package Model;

public class Employee 
{

	private int EmpID;
	private String EmpName;
	private String EmpEmail;
	private String EmpGender;
	private int EmpMobile;
	private String password;
	private int DeptId;
	private int DesigId;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empName, String empEmail, String empGender, int empMobile, String password, int deptId,
			int desigId) {
		super();
		EmpName = empName;
		EmpEmail = empEmail;
		EmpGender = empGender;
		EmpMobile = empMobile;
		this.password = password;
		DeptId = deptId;
		DesigId = desigId;
	}
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getEmpEmail() {
		return EmpEmail;
	}
	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}
	public String getEmpGender() {
		return EmpGender;
	}
	public void setEmpGender(String empGender) {
		EmpGender = empGender;
	}
	public int getEmpMobile() {
		return EmpMobile;
	}
	public void setEmpMobile(int empMobile) {
		EmpMobile = empMobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDeptId() {
		return DeptId;
	}
	public void setDeptId(int deptId) {
		DeptId = deptId;
	}
	public int getDesigId() {
		return DesigId;
	}
	public void setDesigId(int desigId) {
		DesigId = desigId;
	}
	@Override
	public String toString() {
		return "Employee [EmpID=" + EmpID + ", EmpName=" + EmpName + ", EmpEmail=" + EmpEmail + ", EmpGender="
				+ EmpGender + ", EmpMobile=" + EmpMobile + ", password=" + password + ", DeptId=" + DeptId
				+ ", DesigId=" + DesigId + "]";
	}
	
	
}
