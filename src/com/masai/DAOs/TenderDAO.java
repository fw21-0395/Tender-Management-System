package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.TenderException;
import com.masai.Models.Tender;

public interface TenderDAO {

	public String addTender(Tender tender);
	
    public Tender getTender(int id) throws TenderException;
    
    public List<Tender> getAllTenders() throws TenderException;
    
    public List<Tender> getOpenTenders() throws TenderException;
    
    public String updateTender(Tender tender) throws TenderException;
    
    public String deleteTender(int id) throws TenderException;
}
