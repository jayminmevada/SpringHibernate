package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

public class DesignationAddController {
	@FXML
	private TextField txtDesignation;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;

	Connection connection=SqlConnection.getConnection();
	PreparedStatement preparedStatement;
	
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
	
	@FXML
	public void addDesignation(ActionEvent event)
	{

		if(validate())
		{
			String query="insert into tbl_designation(DesigName) values(?)";
			try {
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1,txtDesignation.getText());
				preparedStatement.executeUpdate();
				System.out.println(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDesignation.setText("");
		}

	}
	
	public boolean validate() {
        StringBuilder errorMessage = new StringBuilder();

        if (txtDesignation.getText().isEmpty())
        {
                errorMessage.append("Enter Your Designation Name\n");
           
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
