package model;

public class LoginBean {
	
	// Attributes
	private String uname;
	private String pass;
	
	// Constructor
	public LoginBean(String uname, String pass) {
		super();
		this.uname = uname;
		this.pass = pass;
	}
	
	// Getters/Setters
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	// ToString
	@Override
	public String toString() {
		return "LoginBean [uname=" + uname + ", pass=" + pass + "]";
	}
}
