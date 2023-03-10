package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.AdminException;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.UsernameAndPasswordException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Admin;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;

//Admin DAO Interface:
public interface AdminDAO {

	//Abstract Methods:
	public Admin AdminLogin( String username, String Password ) throws AdminException;
	
	public String RegisterNewVendor( Vendor vendor ) ;
	
	public List<Vendor> GetAllVendors ( ) throws VendorException;
	
	public String AddTender( Tender tender );
	
	public List<Tender> GetAllTenders() throws TenderException;
	
	public List<Bid> GetAllBidsByTender( int TenderID ) throws BidException;
	
	public String AssignTenderToVendor( int TenderID, int VendorID ) throws TenderException, VendorException, BidException;
}
