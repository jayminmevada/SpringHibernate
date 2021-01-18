package Model;

public class Designation {

	private int DesigId;
	private String DesigName;
	public Designation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Designation(String desigName) {
		DesigName = desigName;
	}
	
	public Designation(int desigId, String desigName) {
		super();
		DesigId = desigId;
		DesigName = desigName;
	}
	
	
	public int getDesigId() {
		return DesigId;
	}
	public void setDesigId(int desigId) {
		DesigId = desigId;
	}
	public String getDesigName() {
		return DesigName;
	}
	public void setDesigName(String desigName) {
		DesigName = desigName;
	}
	@Override
	public String toString() {
		return "Designation [DesigId=" + DesigId + ", DesigName=" + DesigName + "]";
	}
	
	
}
