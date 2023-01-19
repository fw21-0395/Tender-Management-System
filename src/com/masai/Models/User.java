package com.masai.Models;

public class User {

	private int Id;
	private String UserName;
	private String Password;
	private String Role;
	
	public User() {
		
	}

	public User(int id, String userName, String password, String role) {
		super();
		Id = id;
		UserName = userName;
		Password = password;
		Role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
		return "User [Id=" + Id + ", UserName=" + UserName + ", Password=" + Password + ", Role=" + Role + "]";
	}
	
}
