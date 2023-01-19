package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.BidException;
import com.masai.Models.Bid;

public interface BidDAO {

	public String addBid(Bid bid);
	
    public Bid getBid(int id) throws BidException;
    
    public List<Bid> getAllBids() throws BidException;
    
    public List<Bid> getBidsByVendor(int vendorId) throws BidException;
    
    public List<Bid> getBidsByTender(int tenderId) throws BidException;
    
    public String updateBid(Bid bid) throws BidException;
    
    public String deleteBid(int id);
}
