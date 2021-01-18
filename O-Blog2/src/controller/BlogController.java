package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.management.modelmbean.ModelMBean;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import Model.AllQueries;
import Model.Department;
import Model.Post;
import Model.PostControllerDetails;
import Model.PostDepartment;
import application.Main;

public class BlogController {
	
	//Change empId.............
	static int empId ;
	
	static List<Post> posts = new ArrayList<>();
	static List<PostControllerDetails> postNodeDetails = new ArrayList<>();
	
	@FXML
	private VBox postList;
	
	@FXML 
	private TextArea postA ;
	
	@FXML
	private Button postB;
	
	@FXML
	private Button infoB;
	
	@FXML
	private ComboBox cBoxDepartment;
	
	@FXML
	private Button btnLogOut;
	
	AdminController adminController;
	
	Connection connection;
	PreparedStatement preparedStatement;
	
	int DeptID=18;
	ObservableList<Model.Department> list=FXCollections.observableArrayList();

	Department dept=new Department();
	@FXML
	private void initialize() throws IOException, SQLException {
		empId = LoginController.obj.getUserId();
		
		System.out.println("blog controller .."+LoginController.obj.getUserId());
		loadPosts();
		loadPostList();
		postAOnClick();
		infoBOnClick();
		LogOut();
		departmentCombo();
	}

	private void departmentCombo() 
	{
	   	connection=SqlConnection.getConnection();
    	String query="select DeptId,DeptName from tbl_department";
    	try {
			preparedStatement=connection.prepareStatement(query);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				dept=new Department();
				dept.setDeptId(rs.getInt("DeptId"));
				dept.setDeptName(rs.getString("DeptName"));
				System.out.println(rs.getInt("DeptId") + rs.getString("DeptName"));
				list.add(new Department(rs.getInt("DeptId"),rs.getString("DeptName")));
			}
		} catch (SQLException e1) 
    	{
			e1.printStackTrace();
		}
    	cBoxDepartment.setItems(list);
    	cBoxDepartment.setConverter(new StringConverter<Department>() {

			@Override
			public String toString(Department object) {
				// TODO Auto-generated method stub
				return object.getDeptName();
			}

			@Override
			public Department fromString(String string) {
				// TODO Auto-generated method stub
				return (Department) cBoxDepartment.getItems().stream().filter(ap->ap.getClass().getName().equals(string)).findFirst().orElse(null);
			}
		});
    	
		cBoxDepartment.valueProperty().addListener((obs, oldval, newval) -> {
		    if(newval != null)
		    {
		    	DeptID=((Department) newval).getDeptId();
		    	System.out.println("===id check .."+DeptID);
		        System.out.println("Selected Department " + ((Department) newval).getDeptName() 
		            + ". ID: " + ((Department) newval).getDeptId());
		    }

		});

		
	}
	
	int DesigId=0;

	private void loadPosts() throws SQLException {
		posts = AllQueries.getAllPost();
	}

	private void infoBOnClick() {
		infoB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(validate())
				{
					String postDesc = postA.getText();
					String postType = "0";
					Date postDate = new Date();
					int postId;
					if(posts.isEmpty()) {
						postId = 1;
					}else {
						postId = posts.get(posts.size()-1).getPostId()+1;
					}
					Post postOb = new Post(postDesc,postType,postDate,empId,postId,0,DeptID);
					try {
						AllQueries.setPostToTable(postOb);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					posts.add(postOb);
					System.out.println(posts.get(posts.size()-1).getPostId());
					//write code to save post in post table
					
					//add new post node to observable list 
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/resources/Post.fxml"));
					VBox post = null;
					try {
						post = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					PostController controller = loader.getController();
					controller.setPostList(postList);
					try {
						controller.setPostId(postOb,post,controller);
					} catch (SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					controller.loadEmpPost(LoginController.obj.getUserName().toString(), postA.getText());
					
					postList.getChildren().add(0,post);
					postA.setText("");
				}

			}
		});
	}

	private void postAOnClick() 
	{
			postB.setOnAction(new EventHandler<ActionEvent>() 
			{

				@Override
				public void handle(ActionEvent arg0) {
					if(validate())
					{
						String postDesc = postA.getText();
						String postType = "1";
						Date postDate = new Date();
						int postId ;
						if(posts.isEmpty()) {
							postId = 1;
						}else {
							postId = posts.get(posts.size()-1).getPostId()+1;
							System.out.println(postId);
						}
						Post postOb = new Post(postDesc,postType,postDate,empId,postId,0,DeptID);
						System.out.println(DeptID);
						try {
							AllQueries.setPostToTable(postOb);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						posts.add(postOb);
						System.out.println(posts.get(posts.size()-1).getPostId());
						//write code to save post in post table
						
						//add new post node to observable list 
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/resources/Post.fxml"));
						VBox post = new VBox();
						try {
							post = loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						PostController controller = loader.getController();
						controller.setPostList(postList);
						try {
							controller.setPostId(postOb,post,controller);
						} catch (SQLException | IOException e) {
							// TODO Auto-generated catch block
							System.out.println("in getcontrpller....");
							e.printStackTrace();
						}

						controller.loadEmpPost(LoginController.obj.getUserName().toString(), postA.getText());
						
						postList.getChildren().add(0,post);
						postA.setText("");
						DeptID=0;
						cBoxDepartment.setValue(null);
					}

				}
				
			});
			
		
	}
	public void LogOut()
	{
		btnLogOut.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) 
			{
				LoginController.obj.cleanUserSession();
				Main main=new Main();
				Main.primaryStage.setTitle("Login");
				main.start(Main.primaryStage);
			}
			
		});
	}

	private void loadPostList() throws SQLException, IOException 
	{

		for(int i=1;i<posts.size();i++) {
			//write query load emp name and store to employeeName
			String employeeName = AllQueries.getEmpName(posts.get(i).getEmpID());
			String postDisplay = posts.get(i).getPostDesc();
			System.out.println("ckeck....1");
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(getClass().getResource("/resources/Post.fxml"));
			System.out.println("ckeck....2");
			VBox post = null;
			try {
				post = loader.load();
				System.out.println("ckeck....3");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PostController controller = loader.getController();
			controller.setPostList(postList);
			System.out.println("ckeck....4");
			controller.setPostId(posts.get(i),post,controller);
			controller.loadEmpPost(employeeName, postDisplay);
			System.out.println("ckeck....5");
			postList.getChildren().add(0,post);
		}
		
	}

	public void AdminController(AdminController adminController2) 
	{
		adminController=adminController2;
		
	}
	public boolean validate() {
        StringBuilder errorMessage = new StringBuilder();

        if (postA.getText().isEmpty())
        {
              errorMessage.append("Enter Your Post First\n");
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
