package Model;

import javafx.scene.layout.VBox;
import controller.PostController;

public class PostControllerDetails {
	
	private PostController controller;
	private VBox node;
	private int postId;
	

	public PostControllerDetails(PostController controller, VBox post, int postId) {
		this.controller = controller;
		this.node = post;
		this.postId = postId;
	}
	
	public PostController getController() {
		return controller;
	}
	
	public VBox getNode() {
		return node;
	}
	
	public int getPostId() {
		return postId;
	}

}
