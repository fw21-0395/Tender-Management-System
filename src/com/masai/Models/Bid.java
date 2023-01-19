package com.masai.Models;

public class Bid {

	private int Id;
	private int VendorId;
	private int TenderId;
	private int BidAmount;
	private String Status;
	
	public Bid() {
		
	}

	public Bid(int id, int vendorId, int tenderId, int bidAmount, String status) {
		super();
		Id = id;
		VendorId = vendorId;
		TenderId = tenderId;
		BidAmount = bidAmount;
		Status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getVendorId() {
		return VendorId;
	}

	public void setVendorId(int vendorId) {
		VendorId = vendorId;
	}

	public int getTenderId() {
		return TenderId;
	}

	public void setTenderId(int tenderId) {
		TenderId = tenderId;
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
		return "Bid [Id=" + Id + ", VendorId=" + VendorId + ", TenderId=" + TenderId + ", BidAmount=" + BidAmount
				+ ", Status=" + Status + "]";
	}
}
