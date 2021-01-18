package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import Model.AllQueries;
import Model.Post;
import Model.ReplyObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostController {
	
	private int id ;
	private VBox postList;
	private VBox node;
	
	@FXML
	private VBox postVbox;
	
	@FXML
	private VBox replyList;
	
	@FXML
	private HBox buttonHbox;
	
	@FXML
	private Label employeeName;
	
	@FXML
	private Label postDisplay;
	
	
	private Button replyB = new Button("reply");
	
	
	private Button updateB = new Button("Update");
	
	
	private Button deleteB = new Button("Delete");
	private PostController controller;
	private Post post;
	
	public void init() throws SQLException, IOException {
		postDisplay.prefWidthProperty().bind(postList.widthProperty());
		postDisplay.setPadding(new Insets(5));
		
		if(BlogController.empId == post.getEmpID()) {
			if(post.getFlag() == 1) {
				buttonHbox.getChildren().add(replyB);
			}
		}else {
			if(post.getPostType().equals("1")) {
				buttonHbox.getChildren().add(replyB);
			}
		}
		
//		if(BlogController.empId == post.getEmpID() && post.getPostType().equals("0")) {
//			buttonHbox.getChildren().addAll(deleteB);
//		}
//		
		//here instead of 0 we have to put login employee id by postlist date
		if(BlogController.empId == post.getEmpID() ) {
			buttonHbox.getChildren().addAll(deleteB);
		}
		loadObReplyList();
	}
	
	
	
	
	@FXML
	private void initialize() throws IOException, SQLException {
		
		//postVbox.prefWidthProperty().bind(postList.widthProperty());
		
		replyB.setStyle("-fx-background-color: #790000; -fx-text-fill: white; -fx-font-size :  15; -fx-font-weight: bold");
		deleteB.setStyle("-fx-background-color: #790000; -fx-text-fill: white; -fx-font-size :  15; -fx-font-weight: bold");
			
		replyB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					AllQueries.setFlag(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/resources/Reply.fxml"));
				AnchorPane layout = null;
				try {
					layout = (AnchorPane) loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				replyController controller = loader.getController();
			
		
				Scene scene = new Scene(layout);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("reply");
				stage.setResizable(false);
				controller.setResource(PostController.this.controller,id, replyList,stage);
				stage.show();
				
			}
		});
		
		updateB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/resources/UpdatePost.fxml"));
				AnchorPane layout = null;
				try {
					layout = (AnchorPane) loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene = new Scene(layout);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Update Post");
				stage.setResizable(false);
				stage.show();
				
			}
		});
		
		deleteB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				//write code to delete post from Blog post Vbox and post table 
				try {
					AllQueries.deletePost(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				postList.getChildren().remove(node);
				int n = BlogController.posts.size();
				for(int i=1;i<n;i++) {
					System.out.println(i);
					if(BlogController.posts.get(i).getPostId() == id) {
						BlogController.posts.remove(i);
						break;
					}
				}
			}
		});
//		obReplyList.add(replyMessage);
//		replyList.getChildren().addAll(obReplyList);
		
	}
	
	protected void setResource(PostController controller2, int id2, VBox replyList2) {
		// TODO Auto-generated method stub
		
	}




	public void loadEmpPost(String employeeName,String postDisplay) {
//		int n = BlogController.posts.size();
//		for(int i=0;i<n;i++) {
//			String empName = null;
//			int empId = BlogController.posts.get(i).getEmpID();
//			//Write query to get emp name from empid and store it in empName
//			employeeName.setText(empName);
//			postDisplay.setText(BlogController.posts.get(i).getPostDesc());
//		}
		this.employeeName.setText(employeeName);
		this.postDisplay.setText(postDisplay);
		
	}

	private void loadObReplyList() throws SQLException, IOException {
		//write query to load all reply related to this post
//		while(!result.next) {
//			String empName = result.getString("empName");
//			String replyData = result.getString("replyData");
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getResource("/views/replyMessage.fxml"));
//			Hbox layout = loader.load();
//			replyMessageController controller = loader.getController();
//			controller.setEmpName(empName);
//			controller.setReplyData(replyData);
//			obReplyList.add(layout);
//		}
		List<ReplyObject> list = new ArrayList<ReplyObject>();
		list = AllQueries.getReplys(id);
		for(int i=1;i<list.size();i++) {
			System.out.println(list.get(i).getEmpId()+" "+list.get(i).getReplyName());
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/resources/ReplyMessage.fxml"));
			VBox layout = loader.load();
			replyMessageController controller = loader.getController();
			String name = AllQueries.getEmpName(list.get(i).getEmpId());
			controller.setEmpName(name);
			controller.setReplyData(list.get(i).getReplyName());
			replyList.getChildren().add(layout);
		}
		
	}

	public void setPostId(Post post,VBox node,PostController controller) throws SQLException, IOException {
		this.post = post;
		this.id = post.getPostId();
		this.node = node;
		this.controller = controller;
		init();
	}
	
	public void setPostList(VBox postList) {
		this.postList = postList;
	}
	
}
