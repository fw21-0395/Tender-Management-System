package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;


public interface VendorDAO {
	
	public String VendorLogin( String username, String password ) throws VendorException;
	
	public List<Tender> GetAllTenders() throws TenderException;
	
	public String PlaceBidOnTender( int BidID, int TenderID  ) throws BidException;
	
	public String CheckStatusOfBid( int BidID ) throws BidException; 
	
	public List<Bid> GetAllBidsByVendor( int VendorID ) throws BidException; 
}
