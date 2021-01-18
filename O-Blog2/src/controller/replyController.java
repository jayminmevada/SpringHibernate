package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Model.AllQueries;
import application.Main;


public class replyController 
{
	@FXML
	private TextArea replyText;
	
	@FXML
	private Button replyButton;

	private PostController contoller;

	private int postId;

	private VBox replyList;

	private Stage stage;
	
	public void setResource(PostController controller,int postId,VBox replyList,Stage stage)
	{
		this.stage = stage;
		this.contoller = controller;
		this.postId = postId;
		this.replyList = replyList;
		replyButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(validate())
				{
					String replyData = replyText.getText();
					try {
						AllQueries.addReply(replyData,postId,BlogController.empId);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/resources/ReplyMessage.fxml"));
					VBox layout = new VBox();
					try {
						layout = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					replyMessageController controller = loader.getController();
					String name = null;
					try {
						name = AllQueries.getEmpName(BlogController.empId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					controller.setEmpName(name);
					controller.setReplyData(replyData);
					replyList.getChildren().add(layout);
					replyText.setText("");
					stage.close();
				}

			}
		});
	}
	public boolean validate() {
        StringBuilder errorMessage = new StringBuilder();

        if (replyText.getText().isEmpty())
        {
              errorMessage.append("Enter Your Reply  First\n");
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
