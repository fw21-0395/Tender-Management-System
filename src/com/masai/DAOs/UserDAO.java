package com.masai.DAOs;

import java.util.List;

import com.masai.Exceptions.UserException;
import com.masai.Models.User;

public interface UserDAO {

	public String addUser(User user);
	
    public User getUser(int id) throws UserException;
    
    public User getUser(String username) throws UserException;
    
    public List<User> getAllUsers() throws UserException;
    
    public String updateUser(User user) throws UserException;
    
    public String deleteUser(int id) throws UserException;
}
