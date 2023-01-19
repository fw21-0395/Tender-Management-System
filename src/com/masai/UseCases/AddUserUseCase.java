package com.masai.UseCases;

import java.util.Scanner;

import com.masai.DAOs.UserDAO;
import com.masai.DAOs.UserDaoImpl;
import com.masai.Models.User;

public class AddUserUseCase {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String username = sc.next();
		
		System.out.println("Enter Password:");
		String password = sc.next();
		
		System.out.println("Enter Role:");
		String role = sc.next();
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setRole(role);
		
		UserDAO dao = new UserDaoImpl();
		
		String Res = dao.addUser(user);
		System.out.println(Res);
	}
}
