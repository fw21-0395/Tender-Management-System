package com.masai.Models;

import java.sql.Date;

public class Tender {

	private int Id;
	private String Titile;
	private String Description;
	private Date Deadline;
	private String Status;
	
	public Tender() {
		
	}

	public Tender(int id, String titile, String description, Date deadline, String status) {
		super();
		Id = id;
		Titile = titile;
		Description = description;
		Deadline = deadline;
		Status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public Date getDeadline() {
		return Deadline;
	}

	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Tendor [Id=" + Id + ", Titile=" + Titile + ", Description=" + Description + ", Deadline=" + Deadline
				+ ", Status=" + Status + "]";
	}
	
}
