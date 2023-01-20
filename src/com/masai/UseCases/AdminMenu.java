package com.masai.UseCases;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.DAOs.AdminDAO;
import com.masai.DAOs.AdminDAOImpl;
import com.masai.Exceptions.AdminException;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;

public class AdminMenu {

	public void Login () {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Admin Credentials( Username and Password )");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter username : ");
		String username = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			
			System.out.println(adminDao.AdminLogin(username, password)); 
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RegisterNewVendor() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Vendor Details( Vendor Name, Username and Password )");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Vendor Name : ");
		String name = sc.next();
		
		System.out.println("Enter username : ");
		String username = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		Vendor vendor = new Vendor();
		
		vendor.setVenderName(name);
		vendor.setUsername(username);
		vendor.setPassword(password);
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		String res = adminDao.RegisterNewVendor(vendor);
		
		System.out.println(res);
		
	}
	
	public void GetAllVendors() {
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			List<Vendor> vendors = adminDao.GetAllVendors();
			
			vendors.forEach( vendor -> {
				
				System.out.println("Vendor ID:"+vendor.getVendorID());
				System.out.println("Vendor Name:"+vendor.getVenderName());
				System.out.println("Vendor Username:"+vendor.getUsername());
				
				System.out.println("-------------------------------");
				
			});
			
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AddTender() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Tender Details( Tender Title, Description, Deadline and Status )");
		System.out.println("-----------------------------------------------");
		
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
		
		String res = adminDao.AddTender(tender);
		
		System.out.println(res);
		
	}
	
	public void GetAllTenders() {
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			List<Tender> tenders = adminDao.GetAllTenders();
			
			tenders.forEach( tender -> {
				
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
				String result = "Not Assigned Yet";
				if(res == 1) {
					
					result = "Assigned to Vendor with ID as "+tender.getVendor_ID();
				}
				
				System.out.println(" Tender Status :"+result);
				
				System.out.println("------------------------------");
				
			});
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AssignTenderToVendor() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Details to Assign Tender to a Vendor");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter TenderID : ");
		int tendorID = sc.nextInt();
		
		System.out.println("Enter VendorID : ");
		int vendorID = sc.nextInt();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			  
			System.out.println(adminDao.AssignTenderToVendor( tendorID, vendorID ));
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
		
		System.out.println("Enter Tender ID to check your all Bids");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Tender ID : ");
		int tenderID = sc.nextInt();
		
		AdminDAO adminDao = new AdminDAOImpl();
		
		try {
			
			List<Bid> bids = adminDao.GetAllBidsByTender(tenderID);
			
			bids.forEach( bid -> {
				
				System.out.println("Bid ID:"+bid.getBidID());
				System.out.println("Vendor ID:"+bid.getVendorID());
				System.out.println("Tender ID:"+bid.getTenderID());
				System.out.println("Bid Amount:"+bid.getBidAmount());
				
				String status = "Not Assigned";
				
				if( bid.getStatus() == 1 ) {
					
					status = "Assigned";
				}
				
				System.out.println("Bid Status:"+status);
				
				System.out.println("------------------------------------------");
				
			});
			
			
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		AdminMenu am = new AdminMenu();
		
//		am.Login();
		
//		am.RegisterNewVendor();
		
//		am.GetAllVendors();
		
//		am.AddTender();
		
//		am.GetAllTenders();
		
//		am.GetAllBidsByTenderID();
		
//		am.AssignTenderToVendor();
		
	}
	
}