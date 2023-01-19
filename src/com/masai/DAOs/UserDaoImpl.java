package com.masai.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Exceptions.UserException;
import com.masai.Models.User;
import com.masai.Utility.DBUtil;

public class UserDaoImpl implements UserDAO {

	@Override
	public String addUser(User user) {
		
		String Response = "Not Added Error !!!";
		
			try ( Connection conn = DBUtil.provideConnection() ) {
				
				PreparedStatement ps = conn.prepareStatement(" Insert INTO Users(UserName,Password,Role) Values(?,?,?) ");
				ps.setString( 1, user.getUserName() );
				ps.setString( 2, user.getPassword() );
				ps.setString( 3, user.getRole() );
				
				int res =  ps.executeUpdate();
				
				if( res > 0 ) {
					
					Response = "User Added Successfully";
				}else {
					
					Response = "User is not Added";
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return Response;
	}

	@Override
	public User getUser(int id) throws UserException {
		
		User Response = null;
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			throw new UserException(e.getMessage());
		}
	
		return Response;
	}

	@Override
	public User getUser(String username) throws UserException {
		
		User Response = null;
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			throw new UserException(e.getMessage());
		}
	
		return Response;
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		
		List<User> Response = new ArrayList();
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
			throw new UserException(e.getMessage());
		}
	
		return Response;
	}

	@Override
	public String updateUser(User user) throws UserException {
		
		String Response = "Not Added Error !!!";
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return Response;
	}

	@Override
	public String deleteUser(int id) throws UserException {
		
		String Response = "Not Added Error !!!";
		
		try ( Connection conn = DBUtil.provideConnection() ) {
			
			PreparedStatement ps = conn.prepareStatement("");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return Response;
	}

}
