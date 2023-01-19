package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.VendorException;
import com.masai.Models.Vendor;

public interface VendorDAO {
	
	public String addVendor(Vendor vendor);
	
    public Vendor getVendor(int id) throws VendorException;
    
    public List<Vendor> getAllVendors() throws VendorException;
    
    public String updateVendor(Vendor vendor) throws VendorException;
    
    public String deleteVendor(int id) throws VendorException;
}
