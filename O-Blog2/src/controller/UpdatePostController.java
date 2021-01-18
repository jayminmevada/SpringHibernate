package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

public class UpdatePostController implements Initializable{

	
    @FXML
    private TextArea txtPost;
    
    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    
    
    
    
    @Override
    public void initialize(URL location , ResourceBundle resources) {
    	
    	
    }
    
    
    public void initializeDashboard() {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root, 500, 500);
			Main.primaryStage.setScene(scene);
			Main.primaryStage.setTitle("Login");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    public boolean updatePost() throws Exception {
    	
    	String UpdatePost = txtPost.getText();
    	
//      -----------      For Connecting with Database -------------		
//        UsersDao usersDao = new UsersDao();
//        try {
//			usersDao.insertUsers(userName, Password, select);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
    	
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(Main.primaryStage);
        alert.setTitle("Update Post");	
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to update?");
        
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
		   
		
        }
        else if (option.get() == ButtonType.CANCEL) {
			alert.close();
		}
		
    	
    	
		return false;
    	
		
		
		
    }
    
    
    
    
    
    
}
