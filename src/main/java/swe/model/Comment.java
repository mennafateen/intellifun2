package swe.model;

public class Comment {
	
	private String thecomment;
	User user;
	
	public Comment(String thecomment, User user) {
		super();
		this.thecomment = thecomment;
		this.user = user;
	}
	
	public Comment() {
		this.thecomment = "";
		this.user = new User();
	}

	public String getThecomment() {
		return thecomment;
	}
	public void setThecomment(String thecomment) {
		this.thecomment = thecomment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
