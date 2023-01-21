package com.masai.UseCases;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.DAOs.AdminDAO;
import com.masai.DAOs.AdminDAOImpl;
import com.masai.Exceptions.AdminException;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.UsernameAndPasswordException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Admin;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;

public class AdminMenu {

	public int Login () throws UsernameAndPasswordException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Admin Credentials( Username and Password )");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		System.out.println("Enter username : ");
		String username = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		int login = 0;
		
		try {
			
			if( username.length() < 6 || password.length() < 6 ) {
				
				throw new UsernameAndPasswordException("\n Invalid Username or Password \n ( Username and password length should be minimum 6 characters ) \n Please try again... ");
			}
			
			Admin admin = adminDao.AdminLogin(username, password);
			
			System.out.println("\nWelcome "+admin.getAdminName());
			
			login = admin.getAdminID();
			
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( UsernameAndPasswordException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return login;
	}
	
	public void RegisterNewVendor() throws UsernameAndPasswordException {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("\nEnter Vendor Details( Vendor Name, Username and Password )");
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			
			System.out.println("Enter Vendor Name : ");
			String name = sc.next();
			
			System.out.println("Enter username : ");
			String username = sc.next();
			
			System.out.println("Enter Password : ");
			String password = sc.next();
			
			if( username.length() < 6 || password.length() < 6 ) {
				
				throw new UsernameAndPasswordException("\n Invalid Username or Password \n ( Username and password length should be minimum 6 characters ) \n Please try again... ");
			}
			
			Vendor vendor = new Vendor();
			
			vendor.setVenderName(name);
			vendor.setUsername(username);
			vendor.setPassword(password);
			
			AdminDAO adminDao = new AdminDAOImpl();
			
			System.out.println("\n" + adminDao.RegisterNewVendor(vendor));
			
		} catch ( UsernameAndPasswordException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
	
	public void GetAllVendors() {
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			List<Vendor> vendors = adminDao.GetAllVendors();
			
			System.out.println("\nVendors List:");
			
			vendors.forEach( vendor -> {
				
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
				System.out.println("Vendor ID:"+vendor.getVendorID());
				System.out.println("Vendor Name:"+vendor.getVenderName());
				System.out.println("Vendor Username:"+vendor.getUsername());
				
//				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
			});
			
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AddTender() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Tender Details( Tender Title, Description, Deadline )");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		System.out.println("Enter Tender Title : ");
		String title = sc.nextLine();
		
		System.out.println("Enter Tender Description : ");
		String desc = sc.nextLine();
		
		System.out.println("Enter Tender Deadline Date ( yyyy-mm-dd ): ");
		String StringDate = sc.nextLine();
		
		LocalDate date = LocalDate.parse(StringDate);
		
		Tender tender = new Tender();
		tender.setVendor_ID(null);
		tender.setTitile(title);
		tender.setDescription(desc);
		tender.setDeadline( date );
		tender.setStatus(0);
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		System.out.println( "\n"+ adminDao.AddTender(tender) );
		
	}
	
	public void GetAllTenders() {
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			List<Tender> tenders = adminDao.GetAllTenders();
			
			System.out.println("\nTendors List:");
			
			tenders.forEach( tender -> {
				
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
				System.out.println(" Tender ID :"+tender.getTenderID());
				
				if( tender.getVendor_ID() == 0 ) {
					
					System.out.println(" Vendor ID : Not assigned yet");
				}else {
					System.out.println(" Vendor ID :"+tender.getVendor_ID());
				}
				
				System.out.println(" Tender Title :"+tender.getTitile());
				System.out.println(" Tender Description :"+tender.getDescription());
				System.out.println(" Tender Deadline :"+tender.getDeadline());
				
				int res = tender.getStatus();
				String result = "Open";
				if(res == 1) {
					
					result = "Closed";
				}
				
				System.out.println(" Tender Status :"+result);
				
//				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
			});
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AssignTenderToVendor() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Details to Assign Tender to a Vendor");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		System.out.println("Enter TenderID : ");
		int tendorID = sc.nextInt();
		
		System.out.println("Enter VendorID : ");
		int vendorID = sc.nextInt();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			  
			System.out.println("\n" + adminDao.AssignTenderToVendor( tendorID, vendorID ));
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetAllBidsByTenderID() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Tender ID to check your all Bids");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		System.out.println("Enter Tender ID : ");
		int tenderID = sc.nextInt();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			
			List<Bid> bids = adminDao.GetAllBidsByTender(tenderID);
			
			bids.forEach( bid -> {
				
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
				System.out.println("Bid ID:"+bid.getBidID());
				System.out.println("Vendor ID:"+bid.getVendorID());
				System.out.println("Tender ID:"+bid.getTenderID());
				System.out.println("Bid Amount:"+bid.getBidAmount());
				
				String status = "Not Assigned";
				
				if( bid.getStatus() == 1 ) {
					
					status = "Assigned";
				}
				
				System.out.println("Bid Status:"+status);
				
//				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
				
			});
			
			
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		AdminMenu am = new AdminMenu();
//		
//		try {
//			am.Login();
//		} catch (UsernameAndPasswordException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		am.RegisterNewVendor();
		
//		am.GetAllVendors();
		
//		am.AddTender();
		
//		am.GetAllTenders();
		
//		am.GetAllBidsByTenderID();
		
//		am.AssignTenderToVendor();
		
	}
	
}
