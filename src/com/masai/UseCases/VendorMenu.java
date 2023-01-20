package com.masai.UseCases;

import java.util.List;
import java.util.Scanner;

import com.masai.DAOs.AdminDAO;
import com.masai.DAOs.AdminDAOImpl;
import com.masai.DAOs.VendorDAO;
import com.masai.DAOs.VendorDAOImpl;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.UsernameAndPasswordException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;
import com.masai.Models.Vendor;

public class VendorMenu {

	public int Login() throws UsernameAndPasswordException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Vendor Credentials( Username and Password )");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		System.out.println("Enter username : ");
		String username = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		int loginID = 0;
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			
			if( username.length() < 6 || password.length() < 6 ) {
				
				throw new UsernameAndPasswordException("\n Invalid Username or Password \n ( Username and password length should be minimum 6 characters ) \n Please try again... ");
			}
			
			Vendor vendor = venderDao.VendorLogin(username, password);
			
			System.out.println( "\nWelcome "+vendor.getVenderName() );
			loginID = vendor.getVendorID();
			  
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return loginID;
	}
	
	public void GetAllCurrentTenders() {
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			
			List<Tender> tenders = venderDao.GetAllCurrentTenders();
			
			System.out.println("\nActive Tendors List:");
			
			tenders.forEach( tender -> {
				
				System.out.println(" Tender ID :"+tender.getTenderID());
				
//				if( tender.getVendor_ID() == 0 ) {
//					
//					System.out.println(" Vendor ID : Not assigned yet");
//				}else {
//					System.out.println(" Vendor ID :"+tender.getVendor_ID());
//				}
				
				System.out.println(" Tender Title :"+tender.getTitile());
				System.out.println(" Tender Description :"+tender.getDescription());
				System.out.println(" Tender Deadline :"+tender.getDeadline());
				
				int res = tender.getStatus();
				String result;
				if(res == 0) {
					
					result = "Open";
				}else {
					
					result = "Closed";
				}
				
				System.out.println(" Tender Status :"+result);
				
				System.out.println("------------------------------");
				
			});
			
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void PlaceBid( int vendorID ) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Bid Details( TenderID, Bid Amount )");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Tender ID : ");
		int tenderID = sc.nextInt();
		
		System.out.println("Enter Bid Amount : ");
		int bidAmount = sc.nextInt();
		
		Bid bid = new Bid();
		bid.setVendorID(vendorID);
		bid.setTenderID(tenderID);
		bid.setBidAmount(bidAmount);
		bid.setStatus(0);
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			
			System.out.println(venderDao.PlaceBidOnTender(bid, vendorID, tenderID));
			
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetBidStatusByBidID( int VendorID ) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Bid ID to Check Status of your Bid");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Your Bid ID : ");
		int bidID = sc.nextInt();
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		 try {
			System.out.println( venderDao.CheckStatusOfBid( bidID,VendorID ) );
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetAllBidsByVenderID( int vendorID ) {
		
		Scanner sc = new Scanner(System.in);
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			List<Bid> bids = venderDao.GetAllBidsByVendor(vendorID);
			
			System.out.println("\nYour Bid History:");
			
			bids.forEach( bid -> {
				
				System.out.println("Bid ID:"+bid.getBidID());
				System.out.println("Vendor ID:"+bid.getVendorID());
				System.out.println("Tender ID:"+bid.getTenderID());
				System.out.println("Bid Amount:"+bid.getBidAmount());
				
				String status = "Not Accepted";
				
				if( bid.getStatus() == 1 ) {
					
					status = "Accepted";
				}
				
				System.out.println("Bid Status:"+status);
				
				System.out.println("------------------------------------------");
				
			});
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		VendorMenu vm = new VendorMenu();
		
//		vm.Login();
		
//		vm.GetAllCurrentTenders();
		
//		vm.PlaceBid();	
		
//		vm.GetBidStatusByBidID();
		
//		vm.GetAllBidsByVenderID();
	}
}
