package com.user.auth;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationTest {
	@Autowired
	UserAuthService service;
	@BeforeAll
	public static void start() {
		System.out.println("Starting test methods.\n"
				+ "==========================================\n");
	}
	@AfterAll
	public static void end() {
		System.out.println("Ending test methods.\n"
				+ "==========================================\n");
	}
	/*
	 * User Details Already Present in the Database
	 * =================================================
	 * +------------+-------------------+-----------+
	 * |user_name 	| email				|password	|
	 * +------------+-------------------+-----------+
	 * |Sample Doe	| sam123@demo.in	|doe456		|
	 * |Test Doe  	| test123@demo.in	|test123	|
	 * |Sample Dane	| sam456@demo.in	|dane456	|
	 * |Test Dane  	| test456@demo.in	|test456	|
	 * +------------+-------------------+-----------+	  
	 * 
	 * 
	 */
	@Test // This test has new user registration with unused email ID and username
	@Order(1)
	public void regTestSuccessReg() {
		UserEntity user = new UserEntity();
		user.setUserName("Random Me");
		user.setPassword("leo123");
		user.setEmail("random1@demo.in");

		assertEquals("Account successfully created!", service.register(user));

		System.out.println("Inside regTestSuccessReg().\n"
				+ "This test has new user registration with unused email ID and username\n");

	}

	@Test // This test checks user registration with already used email ID and user
			// registration doesn't occur
	@Order(2)
	public void regTestUnsuccesfulReg1() {
		UserEntity user = new UserEntity();
		user.setUserName("New User");
		user.setPassword("newbie123");
		user.setEmail("test123@demo.in");

		assertEquals("Email ID already exists! Please use a different email ID", service.register(user));
		
		System.out.println("Inside regTestUnsuccesfulReg1().\n"
				+ "This test checks user registration with already used email ID and user\r\n"
				+ "registration doesn't occur\n");

	}

	@Test // This test checks user registration with already used email ID and user
	// registration doesn't occur
	@Order(3)
	public void regTestUnsuccesfulReg2() {
		UserEntity user = new UserEntity();
		user.setUserName("Sample Doe");
		user.setPassword("sample123");
		user.setEmail("doesample123@demo.in");

		assertEquals("Username already exists! Please use a different username", service.register(user));

		System.out.println("Inside regTestUnsuccesfulReg2().\n"
				+ "This test checks user registration with already used email ID and user\r\n"
				+ "registration doesn't occur\n");

	}
	
	@Test
	@Order(4)
	public void loginImmedAfterReg() {
		String userName="Random Me";
		String password="leo123";

		assertNotNull(service.login(userName, password));
		System.out.println("Inside loginImmedAfterReg().\n"
				+ "New User login just after registration...\nCredentials are correct and Successful User login.\n");
	}
	
	@Test//Credentials are correct and Successful User login
	@Order(5)
	public void loginTestSuccessfulLogin() {
		String userName="Sample Dane";
		String password="dane456";
		
		assertNotNull(service.login(userName, password));
		System.out.println("Inside loginTestSuccessfulLogin().\n"
				+ "Credentials are correct and Successful User login.\n");
	}
	@Test//Username and Password are incorrect and User not logged in
	@Order(6)
	public void loginTestUnsuccessfulLogin1() {
		String userName="Random Dane";
		String password="dane456";
		
		assertNull(service.login(userName, password));
		
		System.out.println("Inside loginTestUnsuccessfulLogin1().\n"
				+ "Username and Password are incorrect and User not logged in.\n");
	}
	@Test//Username is valid but incorrect password and User not logged in
	@Order(7)
	public void loginTestUnsuccessfulLogin2() {
		String userName = "Test Doe ";
		String password = "dane456";

		assertNull(service.login(userName, password));

		System.out.println("Inside loginTestUnsuccessfulLogin1().\n"
				+ "Username is valid but incorrect password and User not logged in.\n");
	}
}
