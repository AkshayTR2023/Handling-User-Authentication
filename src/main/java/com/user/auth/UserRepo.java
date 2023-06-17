package com.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<UserEntity, String> {

	@Query("SELECT user FROM UserEntity user WHERE user.userName = ?1 AND user.password = ?2")
	public UserEntity getUserFromUserNamePassword(String username, String password);

	// ===================================================================//

	@Query("SELECT user FROM UserEntity user WHERE user.email = ?1")
	public UserEntity getUserFromEmail(String email);

	// ===================================================================//

	@Query("SELECT user FROM UserEntity user WHERE user.userName = ?1")
	public UserEntity getUserFromUserName(String username);

	// ===================================================================//
}
