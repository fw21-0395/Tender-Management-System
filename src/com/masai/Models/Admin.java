package com.masai.Models;

public class Admin {

	private int AdminID;
	private String UserName;
	private String Password;
	private String Role;
	
	public Admin() {
		
	}

	public Admin(int adminID, String userName, String password, String role) {
		super();
		AdminID = adminID;
		UserName = userName;
		Password = password;
		Role = role;
	}

	public int getAdminID() {
		return AdminID;
	}

	public void setAdminID(int adminID) {
		AdminID = adminID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	@Override
	public String toString() {
		return "Admin [AdminID=" + AdminID + ", UserName=" + UserName + ", Password=" + Password + ", Role=" + Role
				+ "]";
	}
	
}
