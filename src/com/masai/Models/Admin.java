package com.masai.Models;

//Admin Bean Class:
public class Admin {

	//Admin Attributes:
	private int AdminID;
	private String AdminName;
	private String UserName;
	private String Password;
	
	//Empty Constructor:
	public Admin() {
		
	}

	//Constructor:
	public Admin(int adminID, String adminName, String userName, String password) {
		super();
		AdminID = adminID;
		AdminName = adminName;
		UserName = userName;
		Password = password;
	}

	//Getter and Setter Methods:
	public int getAdminID() {
		return AdminID;
	}

	public void setAdminID(int adminID) {
		AdminID = adminID;
	}

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
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

	//To String Method:
	@Override
	public String toString() {
		return "Admin [AdminID=" + AdminID + ", AdminName=" + AdminName + ", UserName=" + UserName + ", Password="
				+ Password + "]";
	}

}
