package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import application.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import javafx.scene.control.PasswordField;

public class LoginController implements Initializable {
	@FXML
	private TextField txtUserName;
	@FXML
	private PasswordField password;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnRegistration;
	
	private Main main;
	String userType;
	int EmpID;
	public static UserSession obj;
	@Override
	public void initialize(URL location , ResourceBundle resources) {
		
		btnRegistration.setOnAction(event -> {
			
			initializeRegistration();
		});
		
	}
	
	public void initializeRegistration() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Registration.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setTitle("Registration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Connection connection=SqlConnection.getConnection();
	PreparedStatement preparedStatement;
	ObservableList<String> list;
	
	
	public void setMain(Main main) 
	{
		this.main=main;
	}

	@FXML
	public void login(ActionEvent event) throws IOException
	{
		String query="select EmpID,EmpName,EmpEmail,Password,Type from tbl_employee where EmpEmail=? and Password=?";
		try 
		{
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, txtUserName.getText());
			preparedStatement.setString(2, password.getText());
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				int EmpID=Integer.parseInt(rs.getString("EmpID"));
				String EmpName=rs.getString("EmpName");
				userType=rs.getString("Type");
				obj=UserSession.getUser(EmpName, EmpID);
				
			System.out.println("==="+obj.getUserName());

				System.out.println("User ID :"+rs.getString("EmpID"));
				System.out.println("User Name :"+rs.getString("EmpName"));
				System.out.println("Login Success..!!");
				if(userType.equals("Admin"))
				{
					System.out.println("COme At Admin");
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("/resources/Admin.fxml"));
					AnchorPane root=loader.load();
					Scene scene=new Scene(root);
					Stage primaryStage=Main.primaryStage;
					primaryStage.setScene(scene);
					primaryStage.setTitle("Admin Controller");
					primaryStage.setResizable(false);
					primaryStage.show();
				}else
				{
					System.out.println("Come At Normal ..");
					FXMLLoader loader=new FXMLLoader();
					loader.setLocation(getClass().getResource("/resources/Blog.fxml"));
					AnchorPane root=loader.load();
					Scene scene=new Scene(root);
					Stage primaryStage=Main.primaryStage;
					primaryStage.setTitle("Blog");
					primaryStage.setScene(scene);
					primaryStage.setResizable(false);
					primaryStage.show();
				}


			}
			else
			{
				//System.out.println("Login Fail.. !!");
				validate();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public boolean validate() {
        StringBuilder errorMessage = new StringBuilder();

        if (txtUserName.getText().isEmpty()) {
                errorMessage.append("Please Enter Your User Name\n");
        } else if (Pattern
                        .compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$", Pattern.CASE_INSENSITIVE)
                        .matcher(txtUserName.getText()).find() == false) {
                errorMessage.append("Please Enter Valid Email \n");
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
	private void showAlert(Alert.AlertType alertType, String title, String message) 
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(Main.primaryStage);
		alert.show();
	}
}
