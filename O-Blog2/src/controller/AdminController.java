package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {
	@FXML
	private Button btnDepartment;
	@FXML
	private Button btnDesignation;
	@FXML
	private Button btnBlog;
	@FXML
	private Button btnLogOut;
	
	Connection connection=SqlConnection.getConnection();
	PreparedStatement preparedStatement;
	BlogController blogController;
	

	@FXML
	public void addDepartment(ActionEvent event) throws IOException
	{
		
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/resources/DepartmentAdd.fxml"));
		AnchorPane root=loader.load();
		Scene scene=new Scene(root);
		Stage primaryStage=Main.primaryStage;
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Department");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	@FXML
	public void addDesignation(ActionEvent event)
	{
		FXMLLoader loader=new FXMLLoader();
		try {
			
			loader.setLocation(getClass().getResource("/resources/DesignationAdd.fxml"));
			Parent root=loader.load();
			Scene scene=new Scene(root);
			Stage primaryStage=Main.primaryStage;
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Designation");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	@FXML
	public void goBlog(ActionEvent event) throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/resources/Blog.fxml"));
		AnchorPane root=loader.load();
		BlogController controller=loader.getController();
		controller.AdminController(this);
		Scene scene=new Scene(root);
		Stage stage=Main.primaryStage;
		stage.setScene(scene);
		stage.setTitle("Blog");
		stage.setResizable(false);
		stage.show();
		
	}
	@FXML
	public void logOutClick()
	{
		btnLogOut.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LoginController.obj.cleanUserSession();
				Main main=new Main();
				main.start(Main.primaryStage);
				
			}
		});
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		System.out.println("check .."+LoginController.obj.getUserName().toString());
		System.out.println("check .."+LoginController.obj.getUserId());
		System.out.println("hello ...");
		logOutClick();
	}
}
