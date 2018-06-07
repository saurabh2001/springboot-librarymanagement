package com.application.library.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.application.library.model.User;

public interface UserService extends UserDetailsService {
	
	public User findUserByEmail(String email);

	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(Long userId);
	
	public List<User> listAllActiveUsers();
	
	public List<User> listAllUsers();
	
	public User findByUserId(Long userId);

	
}
