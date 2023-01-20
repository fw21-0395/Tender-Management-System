package com.masai.Models;

//Vendor Bean Class
public class Vendor {

	//Vendor Attributes:
	private int VendorID;
	private String Username;
	private String Password;
	private String VenderName;
	
	//Constructor:
	public Vendor() {
		
	}

	public Vendor(int vendorID, String username, String password, String venderName) {
		super();
		VendorID = vendorID;
		Username = username;
		Password = password;
		VenderName = venderName;
	}

	//Getter and Setter Method:
	public int getVendorID() {
		return VendorID;
	}

	public void setVendorID(int vendorID) {
		VendorID = vendorID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getVenderName() {
		return VenderName;
	}

	public void setVenderName(String venderName) {
		VenderName = venderName;
	}

	//To String Method:
	@Override
	public String toString() {
		return "Vendor [VendorID=" + VendorID + ", Username=" + Username + ", Password=" + Password + ", VenderName="
				+ VenderName + "]";
	}
	
}
