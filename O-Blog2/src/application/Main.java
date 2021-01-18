package application;
	

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application 
{
	
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage)  
	{
		this.primaryStage=primaryStage;
		loginCall();
	}

	private void loginCall()
	{
		try
		{
			FXMLLoader loader=new FXMLLoader();	
			loader.setLocation(Main.class.getResource("/resources/Login3.fxml"));
			AnchorPane root=loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			LoginController loginController=loader.getController();
			loginController.setMain(this);
			primaryStage.setTitle("Login");
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
