package Model;

import java.util.Date;

public class ReplyObject {

	private int replyId;
	private int postId;
	private int empId;
	private String replyName;
	private Date replydate;
	
	public ReplyObject(int replyId,int postId,int empId, String replyName, Date replyDate) {
		this.replyId = replyId;
		this.postId = postId;
		this.empId = empId;
		this.replyName = replyName;
		this.replydate = replydate;
	}
	
	public int getReplyId() {
		return replyId;
	}
	
	public int getPostId() {
		return postId;
	}

	public int getEmpId() {
		return empId;
	}
	
	public String getReplyName() {
		return replyName;
	}
	
	public Date getReplydate() {
		return replydate;
	}

}
