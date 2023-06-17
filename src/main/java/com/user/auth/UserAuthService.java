package com.user.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
	@Autowired
	UserRepo repo;

	public UserEntity login(String username, String password) {
		return repo.getUserFromUserNamePassword(username, password);
	}

	public String register(UserEntity user) {
		String userName = user.getUserName();
		String email = user.getEmail();
		UserEntity checkUser1 = repo.getUserFromUserName(userName);
		if (checkUser1 != null)
			return "Username already exists! Please use a different username";
		UserEntity checkUser2 = repo.getUserFromEmail(email);
		if (checkUser2 != null)
			return "Email ID already exists! Please use a different email ID";

		// If the username and email is not already present, add the user and then
		// return a string showing successful registration.
		repo.save(user);
		return "Account successfully created!";
	}
}
