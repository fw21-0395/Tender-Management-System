package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;


public interface VendorDAO {
	
	public Vendor VendorLogin( String username, String password ) throws VendorException;
	
	public List<Tender> GetAllCurrentTenders() throws TenderException;
	
	public String PlaceBidOnTender( Bid bid,int VendorID, int TenderID  ) throws TenderException,VendorException;
	
	public String CheckStatusOfBid( int BidID, int VendorID ) throws BidException; 
	
	public List<Bid> GetAllBidsByVendor( int VendorID ) throws BidException,VendorException; 
}
