package controller;

//For Session we use SingleTone Designe pattern
public final class UserSession 
{
	private static UserSession user;
	
	private String userName;
	private int userId;
	public UserSession()
	{
		
	}
	
	public UserSession(String userName,int userId)
	{
		this.userName=userName;
		this.userId=userId;
	}

	public static UserSession getUser(String userName,int userId) 
	{

		return user=new UserSession(userName, userId);

	}


	public String getUserName() 
	{
		return userName;
	}

	public int getUserId() {
		return userId;
	}

	public void cleanUserSession()
	{
		userName="";
		userId=0;
	}

	@Override
	public String toString() {
		return "UserSession [userName=" + userName + ", userId=" + userId + "]";
	}
}
