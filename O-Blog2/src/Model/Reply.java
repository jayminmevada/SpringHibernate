package Model;

import java.util.Date;

public class Reply 
{
	private int ReplyId;
	private String ReplyName;
	private Date ReplyDate;
	private int PostId;
	private int EmpId;
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reply(String replyName, Date replyDate, int postId, int empId) {
		super();
		ReplyName = replyName;
		ReplyDate = replyDate;
		PostId = postId;
		EmpId = empId;
	}
	public int getReplyId() {
		return ReplyId;
	}
	public void setReplyId(int replyId) {
		ReplyId = replyId;
	}
	public String getReplyName() {
		return ReplyName;
	}
	public void setReplyName(String replyName) {
		ReplyName = replyName;
	}
	public Date getReplyDate() {
		return ReplyDate;
	}
	public void setReplyDate(Date replyDate) {
		ReplyDate = replyDate;
	}
	public int getPostId() {
		return PostId;
	}
	public void setPostId(int postId) {
		PostId = postId;
	}
	public int getEmpId() {
		return EmpId;
	}
	public void setEmpId(int empId) {
		EmpId = empId;
	}
	@Override
	public String toString() {
		return "Reply [ReplyId=" + ReplyId + ", ReplyName=" + ReplyName + ", ReplyDate=" + ReplyDate + ", PostId="
				+ PostId + ", EmpId=" + EmpId + "]";
	}
	
	
}
