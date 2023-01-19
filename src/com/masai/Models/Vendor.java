package com.masai.Models;

public class Vendor {

	private int Id;
	private String Name;
	private String ContactInfo;
	private String SelectionStatus;
	
	public Vendor() {
		
	}

	public Vendor(int id, String name, String contactInfo, String selectionStatus) {
		super();
		Id = id;
		Name = name;
		ContactInfo = contactInfo;
		SelectionStatus = selectionStatus;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(String contactInfo) {
		ContactInfo = contactInfo;
	}

	public String getSelectionStatus() {
		return SelectionStatus;
	}

	public void setSelectionStatus(String selectionStatus) {
		SelectionStatus = selectionStatus;
	}

	@Override
	public String toString() {
		return "Vendor [Id=" + Id + ", Name=" + Name + ", ContactInfo=" + ContactInfo + ", SelectionStatus="
				+ SelectionStatus + "]";
	}
	
}
