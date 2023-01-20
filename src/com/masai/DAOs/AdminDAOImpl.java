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

//Admin DAO Interface Implementation Class:
public class AdminDAOImpl implements AdminDAO {

	//Admin Login Implementation:It will take Username and Password and
	//will Match details in Admins Table present in Database and 
	//return Admin object
	@Override
	public Admin AdminLogin(String username, String Password) throws AdminException {
		
		//Response:
		Admin admin = null;
		
			//Connection:
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				//Prepared Statement: To check Username and Password are Correct or not through MySQL Command
			 	PreparedStatement ps = conn.prepareStatement(" SELECT * FROM Admin WHERE username=? AND password = ? ");
			 	
			 	//Binding Values to Statement
			 	ps.setString(1, username); //1 Refer to 1st ? ( Syntax for placeholder )
			 	ps.setString(2, Password); //2 Refer to 2nd ? ( Syntax for placeholder )
			 	
			 	//ResultSet: executeQuery will return ResultSet
			 	ResultSet rs = ps.executeQuery();
			 	
			 	//If Condition because ResultSet will return 1 Set Only:
			 	if( rs.next() ) {
			 		
			 		//Admin Object for Response:
			 		admin = new Admin(); 
			 		
			 		//Binding values to Admin Object
			 		admin.setAdminID( rs.getInt( "AdminID" ) );
			 		admin.setAdminName( rs.getString( "AdminName" ) );
			 		admin.setUserName( rs.getString( "Username" ) );
			 		admin.setPassword( "Password" );
			 	}else {
			 		
			 		//If details not matched with database details:
			 		throw new AdminException( "Wrong Admin Credentials, Please try again" );
			 	}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new AdminException( e.getMessage() );
			}
		
		//Returned Admin Object as Response	
		return admin;
	}

	//Register New Vendor Implementation:It will take Vendor Object to Register new Vendor and
	//it will insert Vendor Object Details in the Vendors Table and
	//it will return String as Response
	@Override
	public String RegisterNewVendor(Vendor vendor) {
		
		//Response:
		String Response = "Vendor "+vendor.getVenderName()+" Not Regesterd Successffuly !!!";
		
			//Connection:
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				//Prepared Statement: To Insert Vendor Details in Vendor Table using MySQL Commands
				PreparedStatement ps = conn.prepareStatement(" INSERT INTO Vendors( VendorName, Username, Password ) VALUES(?, ?, ?) ");
				ps.setString(1, vendor.getVenderName());
				ps.setString(2, vendor.getUsername());
				ps.setString(3, vendor.getPassword());
				
				//Res: executeUpdate will return int value based on how many rows are affected in Table
				int res = ps.executeUpdate();
				
				//If result value > 0 then MySQL Command is executed successfully
				if(res > 0) {
					
					//Message for response
					Response = "Vendor "+vendor.getVenderName()+" Regesterd Successffuly !!!";
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		//Returned Response Message
		return Response;
	}

	//Get All Vendors List Implementation:It will Collect all Vendors Data from Vendors Table and 
	//it will return List of Vendor Objects as Response
	@Override
	public List<Vendor> GetAllVendors() throws VendorException {
		
		//List of Vendor Objects as Response:
		List<Vendor> vendors = new ArrayList();
		
			//Connection:
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				//Prepared Statement: To get all Rows from Vendors Table using MySQL
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM VENDORS");
				
				//ResultSet: executeQuery will return ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Flag to check if 
				boolean flag = false;
				
				//While Loop because here ResultSet will have more than 1 set
				while( rs.next() ) {
					
					//changed flag to true so no need to throw Exception
					flag = true;
					
					//Vendor Object
					Vendor vendor = new Vendor();
					
					//Binding Values from ResultSet to Vendor Object
					vendor.setVendorID( rs.getInt("VendorID") );
					vendor.setVenderName( rs.getString("VendorName") );
					vendor.setUsername( rs.getString("Username") );
					vendor.setPassword( rs.getString("Password") );
					
					//Added Vendor Object to List
					vendors.add(vendor);
				}
				
				//If Flag is false then throw Vendor Exception
				if (flag == false) {
					
					throw new VendorException( "No Vendor Found in the Database" );
				}
				
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException( e.getMessage() );
			}
		
		//Returning Vendors List as Response
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
				
				if( flag == false ) {
					
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
	public String AssignTenderToVendor(int TenderID, int VendorID) throws TenderException, VendorException,BidException {
		
		String Response = "Tender with ID "+TenderID+" Not Assigned to Vendor with ID "+VendorID;
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				//To Check Status of Tender Already assigned or not
				PreparedStatement ps0 = conn.prepareStatement(" SELECT * FROM Tenders WHERE TenderID = ? ");
				ps0.setInt(1, TenderID);
				
				ResultSet rs0 = ps0.executeQuery();
				
				if( rs0.next() ) {
					
					if( rs0.getInt( "Status" ) == 1 ) {
						
						throw new TenderException( "This Tender is already assigned !!!" );
					}else {
						//To Check VendorID is Correct or Not
						PreparedStatement ps1 = conn.prepareStatement(" SELECT * FROM Vendors WHERE VendorID = ? ");
						ps1.setInt( 1,VendorID );
						
						ResultSet rs1 = ps1.executeQuery();
						
						if( rs1.next() ) {
							
							//To check TenderID is Correct or Not
							PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM Tenders WHERE TenderID = ? ");
							ps2.setInt( 1,TenderID );
							
							ResultSet rs2 = ps2.executeQuery();
							
							if( rs2.next() ) {
								
								//To Check if Vendor has placed bid or not in this tender
								PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM Bids WHERE Vendor_ID = ? AND Tender_ID = ? ");
								ps3.setInt(1, VendorID);
								ps3.setInt(2, TenderID);
								
								ResultSet rs3 = ps3.executeQuery();
								
								if( rs3.next() ) {
									
									int BidID = rs3.getInt("BidID");
									int BidAmount = rs3.getInt("BidAmount");
									
									//To Update in Bids Table
									PreparedStatement ps4 = conn.prepareStatement("UPDATE Bids SET Status = ? WHERE BidID = ?");
									ps4.setInt(1, 1);
									ps4.setInt(2, BidID);
									
									int res4 = ps4.executeUpdate();
									
									if( res4 > 0 ) {
											
										//To Update in Tenders Table
										PreparedStatement ps5 = conn.prepareStatement(" UPDATE Tenders SET Vendor_ID = ?, Status = ? WHERE TenderID = ? ");
										ps5.setInt(1, VendorID);
										ps5.setInt(2, 1);
										ps5.setInt(3, TenderID);
										
										int res5 = ps5.executeUpdate();
										
										if( res5 > 0 ) {
											
											Response = "TenderID "+TenderID+" is Assigned to VendorID "+VendorID +" with BidID "+BidID +" and BidAmount "+BidAmount;
										}
									}
									
								}else {
									
									throw new BidException("No Bid found in the record with VendorID "+VendorID);
								}
								
								
							}else {
								
								throw new TenderException( "No Tender found with ID "+TenderID );
							}
							
						}else {
							
							throw new VendorException( "No Vendor found with ID "+VendorID );
						}
					}
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				
				throw new VendorException(e.getMessage());
			}
		
		return Response;
	}
}
