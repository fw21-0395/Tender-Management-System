package com.masai.Models;

public class Bid {

	private int BidID;
	private int VendorID;
	private int TenderID;
	private int BidAmount;
	private String Status;
	
	public Bid() {
		
	}

	public Bid(int bidID, int vendorID, int tenderID, int bidAmount, String status) {
		super();
		BidID = bidID;
		VendorID = vendorID;
		TenderID = tenderID;
		BidAmount = bidAmount;
		Status = status;
	}

	public int getBidID() {
		return BidID;
	}

	public void setBidID(int bidID) {
		BidID = bidID;
	}

	public int getVendorID() {
		return VendorID;
	}

	public void setVendorID(int vendorID) {
		VendorID = vendorID;
	}

	public int getTenderID() {
		return TenderID;
	}

	public void setTenderID(int tenderID) {
		TenderID = tenderID;
	}

	public int getBidAmount() {
		return BidAmount;
	}

	public void setBidAmount(int bidAmount) {
		BidAmount = bidAmount;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Bid [BidID=" + BidID + ", VendorID=" + VendorID + ", TenderID=" + TenderID + ", BidAmount=" + BidAmount
				+ ", Status=" + Status + "]";
	}
	
}
