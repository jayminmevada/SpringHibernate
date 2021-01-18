package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Model.Department;
import Model.Designation;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RegistrationController implements Initializable{

	
	@FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton radMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radFemale;

    @FXML
    private PasswordField password;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private ComboBox cBoxDepartment;
    
    @FXML
    private ComboBox cBoxUType;

    @FXML
    private ComboBox cBoxDesignation;

    @FXML
    private Button btnSubmit;
    
    @FXML
    private Button btnCancel;
    
	private String selectedGender = "";
	
	Connection connection;
	PreparedStatement preparedStatement;
	ObservableList<Model.Department> list=FXCollections.observableArrayList();
	ObservableList<Model.Designation> list1=FXCollections.observableArrayList();

	Department dept=new Department();
	Designation desig=new Designation();
    @Override
    public void initialize(URL location , ResourceBundle resources) 
    {
    	DepartmentSHow();
    	DesignationShow();
    	userTypeShow();

    	
    	btnSubmit.setOnAction(event -> {
    		
    		try {
    			
				if (validate()) {
					if(registration()) 
					{
						initializeLogin();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
    	
    	
    	btnCancel.setOnAction(event -> { 
    		
    		initializeLogin();
    	});
    		    	
    }
    
    
    private void userTypeShow() 
    {
    	ObservableList<String> listType;
    	listType=cBoxUType.getItems();
    	listType.add("Admin");
    	listType.add("Normal");
	}


	private void DesignationShow() 
    {
     	String query1="select DesigId,DesigName from tbl_designation";
    	try
    	{
			preparedStatement=connection.prepareStatement(query1);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				list1.add(new Designation(rs.getInt("DesigId"),rs.getString("DesigName")));
			}
    	} catch (SQLException e1) 
    	{
			e1.printStackTrace();
		}
    	cBoxDesignation.setItems(list1);
    	
    	
    	cBoxDesignation.setConverter(new StringConverter<Designation>() {

			@Override
			public String toString(Designation object) {
				return object.getDesigName();
			}

			@Override
			public Designation fromString(String string) {
				// TODO Auto-generated method stub
				return (Designation) cBoxDesignation.getItems().stream().filter(ap->ap.getClass().getName().equals(string)).findFirst().orElse(null);
			}
		});
		cBoxDesignation.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null)
		    {
		    	DesigId=((Designation) newval).getDesigId();
		    	System.out.println("===id check .."+DesigId);
		        System.out.println("Selected Designation: " + ((Designation) newval).getDesigName()
		            + ". ID: " + ((Designation) newval).getDesigName());
		    }

		});
		
	}
		
	private void DepartmentSHow() 
    {
    	connection=SqlConnection.getConnection();
    	String query="select DeptId,DeptName from tbl_department";
    	try {
			preparedStatement=connection.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				dept=new Department();
				dept.setDeptId(rs.getInt("DeptId"));
				dept.setDeptName(rs.getString("DeptName"));
				System.out.println(rs.getInt("DeptId") + rs.getString("DeptName"));
				list.add(new Department(rs.getInt("DeptId"),rs.getString("DeptName")));
			}
		} catch (SQLException e1) 
    	{
			e1.printStackTrace();
		}
    	cBoxDepartment.setItems(list);
    	cBoxDepartment.setConverter(new StringConverter<Department>() {

			@Override
			public String toString(Department object) {
				// TODO Auto-generated method stub
				return object.getDeptName();
			}

			@Override
			public Department fromString(String string) {
				// TODO Auto-generated method stub
				return (Department) cBoxDepartment.getItems().stream().filter(ap->ap.getClass().getName().equals(string)).findFirst().orElse(null);
			}
		});
    	
		cBoxDepartment.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null)
		    {
		    	DeptID=((Department) newval).getDeptId();
		    	System.out.println("===id check .."+DeptID);
		        System.out.println("Selected airport: " + ((Department) newval).getDeptName() 
		            + ". ID: " + ((Department) newval).getDeptId());
		    }

		});
		
	}
	int DeptID=0;
	int DesigId=0;

	private void initializeLogin() 
	{
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Login3.fxml"));
//		Parent root;
//		try {
//			root = loader.load();
//			Scene scene = new Scene(root, 350, 350);
//			Main.primaryStage.setScene(scene);
//			Main.primaryStage.setTitle("Login");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Stage primaryStage=Main.primaryStage;
		Main obj=new Main();
		obj.start(primaryStage);
	}
    
    
    public boolean registration() throws Exception
    {

		String Name = txtName.getText();
		String Email = txtEmail.getText();
		String gender = selectedGender;
		String MobileNo = txtMobileNo.getText();
		String Password = password.getText();

		String query4="insert into tbl_employee(EmpName,EmpEmail,EmpGender,EmpMobile,Password,DeptId,DesigId,type)"
				+ "values (?,?,?,?,?,?,?,?)";
		preparedStatement=connection.prepareStatement(query4);
		preparedStatement.setString(1, Name);
		preparedStatement.setString(2, Email);
		preparedStatement.setString(3, selectedGender);
		preparedStatement.setString(4, MobileNo);
		preparedStatement.setString(5, Password);
		preparedStatement.setInt(6, DeptID);
		preparedStatement.setInt(7, DesigId);
		preparedStatement.setString(8, cBoxUType.getValue().toString());
		preparedStatement.executeUpdate();
		
		return true;
		
    }
		public boolean validate() {
			
			StringBuilder errorMessage = new StringBuilder();
			
			if (txtName.getText().isEmpty()) {
				errorMessage.append("Please Enter Your Name\n");
			}
			if (txtEmail.getText().isEmpty()) {
				errorMessage.append("Please Enter Your Email\n");		
			}else if(Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$", Pattern.CASE_INSENSITIVE).matcher(txtEmail.getText()).find()==false) {
				errorMessage.append("Please Enter Valid Email \n");
			} 
			if (radMale.isSelected() == false && radFemale.isSelected() == false) {
				errorMessage.append("Please Select Your Gender\n");
			}
			else{
				selectedGender = radMale.isSelected() ? "Male" : "Female";
			}
			if (txtMobileNo.getText().isEmpty()) {
				errorMessage.append("Please Enter Your Mobile No\n");
			}else if(Pattern.matches("[789]{1}[0-9]{9}", txtMobileNo.getText())==false) {
				errorMessage.append("Please Enter Valid Mobile No\n");
			}
			if (cBoxDepartment.getSelectionModel().isEmpty()) {
				errorMessage.append("Please Select Your Department\n");		
			}
			if (cBoxDesignation.getSelectionModel().isEmpty()) {
				errorMessage.append("Please Select Your Designation\n");		
			}
			if (password.getText().isEmpty()) {
				errorMessage.append("Please Enter Your Password\n");
			}
			
			if (errorMessage.length() != 0) {
				showAlert(Alert.AlertType.ERROR, "Form Error!", errorMessage.toString());
				return false;
			}
			return true;
		}

		private void showAlert(Alert.AlertType alertType, String title, String message) {
			// TODO Auto-generated method stub
			Alert alert = new Alert(alertType);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.initOwner(Main.primaryStage);
			alert.show();
		}
	}

