package com.masai.Models;

import java.time.LocalDate;

//Tender Bean Class:
public class Tender {

	//Tender Attributes:
	private int TenderID;
	private Integer Vendor_ID;
	private String Titile;
	private String Description;
	private LocalDate Deadline;
	private int Status;
	
	//Constructors:
	public Tender() {
		
	}

	public Tender(int tenderID, Integer vendor_ID, String titile, String description, LocalDate deadline, int status) {
		super();
		TenderID = tenderID;
		Vendor_ID = vendor_ID;
		Titile = titile;
		Description = description;
		Deadline = deadline;
		Status = status;
	}

	//Getter and Setter Methods:
	public int getTenderID() {
		return TenderID;
	}

	public void setTenderID(int tenderID) {
		TenderID = tenderID;
	}

	public Integer getVendor_ID() {
		return Vendor_ID;
	}

	public void setVendor_ID(Integer vendor_ID) {
		Vendor_ID = vendor_ID;
	}

	public String getTitile() {
		return Titile;
	}

	public void setTitile(String titile) {
		Titile = titile;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDate getDeadline() {
		return Deadline;
	}

	public void setDeadline(LocalDate deadline) {
		Deadline = deadline;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	//To String Method:
	@Override
	public String toString() {
		return "Tender [TenderID=" + TenderID + ", Vendor_ID=" + Vendor_ID + ", Titile=" + Titile + ", Description="
				+ Description + ", Deadline=" + Deadline + ", Status=" + Status + "]";
	}
	
}
