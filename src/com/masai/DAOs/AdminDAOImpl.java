package com.masai.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Exceptions.AdminException;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Admin;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;
import com.masai.Utility.DBUtil;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public String AdminLogin(String username, String Password) throws AdminException {
		
		String Response = "Wrong Credentials, Please try again...";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
			 	PreparedStatement ps = conn.prepareStatement(" SELECT * FROM Admin WHERE username=? AND password = ? ");
			 	ps.setString(1, username);
			 	ps.setString(2, Password);
			 	
			 	ResultSet rs = ps.executeQuery();
			 	
			 	if( rs.next() ) {
			 		
			 		Response = "Welcome "+ rs.getString("AdminName") +" !"; 
			 		
			 	}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new AdminException( e.getMessage() );
			}
		
		return Response;
	}

	@Override
	public String RegisterNewVendor(Vendor vendor) {
		
		String Response = "Vendor "+vendor.getVenderName()+" Not Regesterd Successffuly !!!";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement(" INSERT INTO Vendors( VendorName, Username, Password ) VALUES(?, ?, ?) ");
				ps.setString(1, vendor.getVenderName());
				ps.setString(2, vendor.getUsername());
				ps.setString(3, vendor.getPassword());
				
				int res = ps.executeUpdate();
				
				if(res > 0) {
					
					Response = "Vendor "+vendor.getVenderName()+" Regesterd Successffuly !!!";
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return Response;
	}

	@Override
	public List<Vendor> GetAllVendors() throws VendorException {
		
		List<Vendor> vendors = new ArrayList();
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM VENDORS");
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = false;
				
				while( rs.next() ) {
					
					Vendor vendor = new Vendor();
					
					vendor.setVendorID( rs.getInt("VendorID") );
					vendor.setVenderName( rs.getString("VendorName") );
					vendor.setUsername( rs.getString("Username") );
					vendor.setPassword( rs.getString("Password") );
					
					vendors.add(vendor);
				}
				
				if (flag = false) {
					
					throw new VendorException( "No Vendor Found in the Database" );
				}
				
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException( e.getMessage() );
			}
		
		return vendors;
	}

	@Override
	public String AddTender(Tender tender) {
		
		String Response = "Tender "+tender.getTitile()+" not Added Successfully !!!";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("INSERT INTO Tenders( Title, Description, Deadline, Status ) VALUES ( ?, ?, ?, ? )");
				ps.setString( 1, tender.getTitile() );
				ps.setString( 2, tender.getDescription() );
				ps.setDate( 3, java.sql.Date.valueOf( tender.getDeadline() ) );
				ps.setInt( 4, tender.getStatus() );
				
				int res =  ps.executeUpdate();
				
				if(res > 0) {
					
					Response = "Tender "+tender.getTitile()+" Added Successfully..."; 
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return Response;
	}

	@Override
	public List<Tender> GetAllTenders() throws TenderException {
		
		List<Tender> tenders = new ArrayList();
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tenders");
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = false;
				
				while( rs.next() ) {
					
					flag =  true;
					
					Tender tender = new Tender();
					
					tender.setTenderID( rs.getInt("TenderID") );
					tender.setVendor_ID( rs.getInt("Vendor_ID") );
					tender.setTitile( rs.getString("Title") );
					tender.setDescription( rs.getString("Description") );
					tender.setDeadline( rs.getDate("Deadline").toLocalDate() );
					tender.setStatus( rs.getInt( "Status" ) );
					
					tenders.add(tender);
				}
				
				if( flag = false ) {
					
					throw new TenderException("No Tender Data found in the Database !!!");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new TenderException(e.getMessage());
			}
		
		return tenders;
	}

	@Override
	public List<Bid> GetAllBidsByTender(int TenderID) throws BidException {
		
		List<Bid> bids = new ArrayList();
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bids WHERE Tender_ID = ?");
			ps.setInt(1, TenderID);
			
			ResultSet rs =  ps.executeQuery();
			
			boolean flag = false;
			
			while( rs.next() ) {
				
				flag = true;
				
				Bid bid =  new Bid();
				bid.setBidID( rs.getInt( "BidID" ) );
				bid.setVendorID( rs.getInt( "Vendor_ID" ) );
				bid.setTenderID( rs.getInt( "Tender_ID" ) );
				bid.setBidAmount( rs.getInt( "BidAmount" ) );
				bid.setStatus( rs.getInt( "Status" ) );
				
				bids.add(bid);
				
			}
			
			if( flag == false ) {
				
				throw new BidException("No Bid found in the record with TenderID "+TenderID);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			throw new BidException(e.getMessage());
		}
		
		return bids;
	}

	@Override
	public String AssignTenderToVendor(int TenderID, int VendorID) throws TenderException, VendorException {
		
		String Response = "Tender with ID "+TenderID+" Not Assigned to Vendor with ID "+VendorID;
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement(" SELECT * FROM Vendors WHERE VendorID = ? ");
				ps.setInt( 1,VendorID );
				
				ResultSet rs = ps.executeQuery();
				
				if( rs.next() ) {
					
					PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Tenders WHERE TenderID = ? ");
					ps2.setInt( 1,TenderID );
					
					ResultSet rs2 = ps2.executeQuery();
					
					if( rs.next() ) {
						
						PreparedStatement ps3 = conn.prepareStatement(" UPDATE Tenders SET Vendor_ID = ? ");
						ps3.setInt(1, VendorID);
						
						int res = ps3.executeUpdate();
						
						if( res > 0 ) {
							Response = "Tender with ID "+TenderID+" Assigned to Vendor with ID "+VendorID;
						}
						
					}else {
						
						throw new TenderException( "No Tender found with ID "+TenderID );
					}
					
					
				}else {
					
					throw new VendorException( "No Vendor found with ID "+VendorID );
				}
				
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException(e.getMessage());
			}
		
		return Response;
	}

}
