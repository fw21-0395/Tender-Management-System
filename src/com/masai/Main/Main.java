package com.masai.Main;

import java.util.Scanner;

import com.masai.UseCases.AdminMenu;
import com.masai.UseCases.VendorMenu;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static AdminMenu am = new AdminMenu();
	static VendorMenu vm = new VendorMenu();
	
	public static void main(String[] args) {
		
		System.out.println("\nWelcome to the Tender Management System ");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
		
		Menu();
	}

	public static void Menu() {
		
		System.out.print(
				 "+-----------------------------+"+"\n"+
				 "| 1. Login as Admin  	      |"+"\n"
				+"| 2. Login as Vendor          |"+"\n"
				+"| 3. Exit                     |"+"\n"
				+"+-----------------------------+"+"\n"
				);
		
		try {
			
			String input = sc.next();
			int option = Integer.parseInt(input);
			
			switch (option) {
			case 1: {
				
				int adminID = am.Login();
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
				if( adminID != 0 ) {
					System.out.println("Logged In as Admin Sucssfully...");
					AdminActivity( adminID );
				}else {
					Menu();
				}
				break;
			}
			case 2: {
				
				int venderID = vm.Login();
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
				if( venderID != 0 ) {
					System.out.println("Logged In as Vendor Sucssfully...");
					VendorActivity( venderID );
				}else {
					Menu();
				}
				break;
			}
			case 3:{
				
				System.out.println("Thank you for using our application !!!");
				break;
			}
			default:
				System.out.println("Invalid Option , Please try again !!!");
				Menu();
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid Option, Please try again");
			Menu();
		}
	}
	
	public static void AdminActivity( int adminID ) {
		
		  System.out.println("+-----------------------------+"+"\n"+
							 "| 1. Register a new Vendor    |"+"\n"
							+"| 2. Get all Vendors          |"+"\n"
							+"| 3. Add new Tender           |"+"\n"
							+"| 4. Get all Tenders          |"+"\n"
							+"| 5. Get all Bids by Tender   |"+"\n"
							+"| 6. Assign Tender to Vendor  |"+"\n"
							+"| 7. Logout                   |"+"\n"
							+"| 8. Exit                     |"+"\n"
							+"+-----------------------------+"+"\n"
							);
		  
		  try {
			  
			  String input = sc.next();
			  int option = Integer.parseInt(input);
			  
			  switch (option) {
			case 1: {
				am.RegisterNewVendor();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 2:{
				am.GetAllVendors();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 3:{
				am.AddTender();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 4:{
				am.GetAllTenders();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 5:{
				am.GetAllBidsByTenderID();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 6:{
				am.AssignTenderToVendor();
				System.out.println("-------------------");
				AdminActivity(adminID);
				break;
			}
			case 7:{
				System.out.println("Logged Out Successfully !");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
				Menu();
				break;
			}
			case 8:{
				System.out.println("Thank you for using our application !!!");
				break;
			}
			default:
				System.out.println("Invalid Option , Please try again !!!");
				AdminActivity( adminID );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid Option, Please try again");
			AdminActivity(adminID);
		}
		
	}
	
	public static void VendorActivity( int venderID ) {
		
		  System.out.println("+-----------------------------+"+"\n"+
							 "| 1. Get all active Tenders   |"+"\n"
							+"| 2. Place a Bid on Tender    |"+"\n"
							+"| 3. Get Bid Status           |"+"\n"
							+"| 4. Get All Bid History      |"+"\n"
							+"| 5. Logout                   |"+"\n"
							+"| 6. Exit                     |"+"\n"
							+"+-----------------------------+"+"\n"
							);
		
		  try {
			  
			  String input = sc.next();
			  int option = Integer.parseInt(input);
			  
			  switch (option) {
			case 1: {
				vm.GetAllCurrentTenders();
				System.out.println("-------------------");
				VendorActivity(venderID);
				break;
			}
			case 2:{
				vm.PlaceBid( venderID );
				System.out.println("-------------------");
				VendorActivity(venderID);
				break;
			}
			case 3:{
				vm.GetBidStatusByBidID( venderID );
				System.out.println("-------------------");
				VendorActivity(venderID);
				break;
			}
			case 4:{
				vm.GetAllBidsByVenderID( venderID );
				System.out.println("-------------------");
				VendorActivity(venderID);
				break;
			}
			case 5:{
				System.out.println("Logged Out Successfully !");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
				Menu();
				break;
			}
			case 6:{
				System.out.println("Thank you for using our application !!!");
				break;
			}
			default:
				System.out.println("Invalid Option , Please try again !!!");
				VendorActivity(venderID);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid Option, Please try again");
			VendorActivity(venderID);
		}
	}
}


