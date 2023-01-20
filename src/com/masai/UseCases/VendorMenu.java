package com.masai.UseCases;

import java.util.List;
import java.util.Scanner;

import com.masai.DAOs.AdminDAO;
import com.masai.DAOs.AdminDAOImpl;
import com.masai.DAOs.VendorDAO;
import com.masai.DAOs.VendorDAOImpl;
import com.masai.Exceptions.BidException;
import com.masai.Exceptions.TenderException;
import com.masai.Exceptions.VendorException;
import com.masai.Models.Bid;
import com.masai.Models.Tender;

public class VendorMenu {

	public void Login() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Vendor Credentials( Username and Password )");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter username : ");
		String username = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			
			  System.out.println(venderDao.VendorLogin(username, password));
			  
		} catch (VendorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetAllCurrentTenders() {
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			
			List<Tender> tenders = venderDao.GetAllCurrentTenders();
			
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
				String result;
				if(res == 0) {
					
					result = "Not Completed";
				}else {
					
					result = "Completed";
				}
				
				System.out.println(" Tender Status :"+result);
				
				System.out.println("------------------------------");
				
			});
			
		} catch (TenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void PlaceBid() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Bid Details( VendorID, TenderID, Bid Amount )");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Your Vendor ID : ");
		int vendorID = sc.nextInt();
		
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
	
	public void GetBidStatusByBidID() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Bid ID to Check Status of your Bid");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Your Bid ID : ");
		int bidID = sc.nextInt();
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		 try {
			System.out.println( venderDao.CheckStatusOfBid(bidID) );
		} catch (BidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetAllBidsByVenderID() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Vendor ID to check your all Bids");
		System.out.println("-----------------------------------------------");
		
		System.out.println("Enter Your Vendor ID : ");
		int vendorID = sc.nextInt();
		
		VendorDAO venderDao = new VendorDAOImpl();
		
		try {
			List<Bid> bids = venderDao.GetAllBidsByVendor(vendorID);
			
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
