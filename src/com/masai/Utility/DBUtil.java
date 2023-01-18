package com.masai.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//DBUtil Class for Connection
public class DBUtil {
	
	private static String drivername;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		
		ResourceBundle rb = ResourceBundle.getBundle("dbDetails");
		
		drivername = rb.getString("db.driverName");
		url = rb.getString("db.url");
		username = rb.getString("db.username");
		password = rb.getString("db.password");
	}
	
	//This method is static so it can be use by anyone to get Connection;
	public static Connection provideConnection() {
		
		//Connection Logic:
		
		//Step 1: Connection Obj 
		Connection conn = null;
		
		//Step 2:Driver
		try {
			
			//class.Forname to set path to Driver
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//Step 3:URL
		
		//Step 4:Driver Manager
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//Returning Connection
		return conn;
	}
	
}
