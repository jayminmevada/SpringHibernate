package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class replyMessageController {
	
	@FXML 
	private Label replyerName ;
	
	@FXML
	private Label replyMessage;

	public void setEmpName(String empName) {
		this.replyerName.setText(empName);
	}

	public void setReplyData(String replydata) {
		this.replyMessage.setText(replydata);
	}
}
