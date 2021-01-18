package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DepartmentAddController 
{
	@FXML
	private TextField txtDept;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnAdd;
	Connection connection;
	PreparedStatement preparedStatement; 
	Stage primaryStage;
	
	@FXML
	public void initialize() {
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(getClass().getResource("/resources/Admin.fxml"));
				AnchorPane root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene=new Scene(root);
				Stage primaryStage=Main.primaryStage;
				primaryStage.setScene(scene);
				primaryStage.setTitle("Admin Controller");
				primaryStage.setResizable(false);
				primaryStage.show();
			}
		});
	}
	
	public void addDepartment(ActionEvent event)
	{	
		if(validate())
		{
			connection=SqlConnection.getConnection();
			String query="insert into tbl_department(DeptName) values (?)";
			try
			{
				preparedStatement=(PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, txtDept.getText());
				preparedStatement.executeUpdate();
				System.out.println(query);
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			txtDept.setText("");
		}
	}
	
	public boolean validate() {
        StringBuilder errorMessage = new StringBuilder();

        if (txtDept.getText().isEmpty())
        {
                errorMessage.append("Enter Your Department Name\n");
           
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
