package com.masai.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;
import com.masai.Utility.DBUtil;

public class VendorDAOImpl implements VendorDAO {

	@Override
	public Vendor VendorLogin(String username, String password) throws VendorException {
		
		Vendor vendor = null;
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vendors WHERE Username = ? AND Password = ?");
				ps.setString(1, username);
				ps.setString(2, password);
				
				ResultSet rs = ps.executeQuery();
				
				if( rs.next() ) {
					
					vendor = new Vendor();
					
					vendor.setVendorID( rs.getInt( "VendorID" ) );
					vendor.setVenderName( rs.getString( "VendorName" ) );
					vendor.setUsername( rs.getString( "Username" ) );
					vendor.setPassword( rs.getString( "Password" ) );
					
				}else {
					
					throw new VendorException("Wrong Vendor Credentials, Please Try Again !!!");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException( e.getMessage() );
			}
		
		return vendor;
	}

	@Override
	public List<Tender> GetAllCurrentTenders() throws TenderException {
		
		List<Tender> tenders = new ArrayList();
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tenders WHERE Vendor_ID is NULL");
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = false;
				
				while( rs.next() ) {
					
					flag = true;
					
					Tender tender = new Tender();
					
					tender.setTenderID( rs.getInt( "TenderID" ) );
					tender.setVendor_ID( rs.getInt( "Vendor_ID" ) );
					tender.setTitile( rs.getString( "Title" ) );
					tender.setDescription( rs.getString( "Description" ) );
					tender.setDeadline( rs.getDate( "Deadline" ).toLocalDate() );
					tender.setStatus( rs.getInt( "Status" ) );
					
					tenders.add(tender);
					
				}
				
				if(flag == false ) {
					
					throw new TenderException("No Tender found in the record !!!");
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new TenderException(e.getMessage());
			}
		
		return tenders;
	}

	@Override
	public String PlaceBidOnTender(Bid bid,int VendorID, int TenderID) throws TenderException,VendorException {
		
		String Response = "Your bid failed to place, please try again...";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps0 = conn.prepareStatement("SELECT * FROM Bids WHERE Tender_ID = ? AND Vendor_ID = ?");
				ps0.setInt(1, TenderID);
				ps0.setInt(2, VendorID);
				
				ResultSet rs0 = ps0.executeQuery();
				
				if( rs0.next() ) {
					
					throw new VendorException(" You already placed a bid on Tender with ID "+TenderID );
				}else {
					
					PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vendors WHERE VendorID = ?");
					ps.setInt(1, VendorID);
					
					ResultSet rs = ps.executeQuery();
					
					if( rs.next() ) {
						
						PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Tenders WHERE TenderID = ? AND Status = ?");
						ps2.setInt(1, TenderID);
						ps2.setInt(2, 0);
						
						ResultSet rs2 = ps2.executeQuery();
						
						if( rs2.next() ) {
							
							PreparedStatement ps3 = conn.prepareStatement( "INSERT INTO BIDS( Vendor_ID, Tender_ID, BidAmount, Status ) VALUES( ?, ?, ?, ? ) " );
							ps3.setInt( 1, VendorID );
							ps3.setInt( 2, TenderID );
							ps3.setInt( 3, bid.getBidAmount() );
							ps3.setInt( 4, bid.getStatus() );
							
							int res = ps3.executeUpdate();
							
							if( res > 0 ) {
								
								Response = "Your bid placed successfully. Thankyou !";
							}
						}else {
							
							throw new TenderException( "Tender with Id "+TenderID+" not found !!!" );
						}
						
					}else {
						
						throw new VendorException("Vendor with ID "+VendorID+" not found !!!");
					}	
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException(e.getMessage());
			}
		
		return Response;
	}

	@Override
	public String CheckStatusOfBid(int BidID, int VendorID ) throws BidException {
		
		String Response = "Not Accepted";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bids WHERE BidID = ? AND Vendor_ID = ?");
				ps.setInt(1, BidID);
				ps.setInt(2, VendorID);
				
				ResultSet rs = ps.executeQuery();
				
				if( rs.next() ) {
					
					if( rs.getInt( "Status" ) == 1 ) {
						Response = "Accepted";
					}
				}else {
					
					throw new BidException( "No Bid found with ID "+BidID  );
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new BidException( e.getMessage() );
			}
		
		return Response;
	}

	@Override
	public List<Bid> GetAllBidsByVendor(int VendorID) throws BidException, VendorException {
		
		List<Bid> bids = new ArrayList();
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bids WHERE Vendor_ID = ?");
				ps.setInt(1, VendorID);
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = false;
				
				while( rs.next() ) {
					
					flag = true;
					
					Bid bid = new Bid();
					
					bid.setBidID( rs.getInt( "BidID" ) );
					bid.setVendorID( rs.getInt( "Vendor_ID" ) );
					bid.setTenderID( rs.getInt( "Tender_ID" ) );
					bid.setBidAmount( rs.getInt( "BidAmount" ) );
					bid.setStatus( rs.getInt( "Status" ) );
					
					bids.add( bid );
				}
				
				if( flag == false ) {
					
					throw new BidException( "No Bid found in the record with Vendor ID "+VendorID );
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException( e.getMessage() );
			}
		
		return bids;
	}

}
