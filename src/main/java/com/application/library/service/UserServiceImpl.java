package com.application.library.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.library.dao.UserBookDao;
import com.application.library.dao.UserDao;
import com.application.library.model.User;
import com.application.library.model.UserBook;

@Service
public class UserServiceImpl implements  UserService {

	@Autowired
	private UserDao userDao;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Value("${lib.adminUsername}")
	private String adminUsername;
	
    @Value("${lib.adminPassword}")
	private String adminPassword;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	public User findUserByEmail(String email) {
		return userDao.findByUseremail(email);
	}
	
	@Override
	public User findByUserId(Long userId)
	{
		return userDao.findById(userId).get();
	}
	
	@Override
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.save(user);

	}
	
	@Override
	public void updateUser(User user) {
		userDao.save(user);

	}
	
	@Override
	public void deleteUser(Long userId) {
		User u = findByUserId(userId);
		userDao.delete(u);

	}
	
	@Override
	public List<User> listAllActiveUsers() {
		List<User> list = new ArrayList<User>();
		userDao.findByActiveTrue().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public List<User> listAllUsers() {
		List<User> list = new ArrayList<User>();
		userDao.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		if(userId.equals(adminUsername))
		{
			org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User
					(adminUsername, bCryptPasswordEncoder.encode(adminPassword), getAdminAuthority());
			return u ;
		}
		
		User user = userDao.findByUseremail(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User
				(user.getUseremail(), user.getPassword(), getUserAuthority());
		
	}
	private List<SimpleGrantedAuthority> getUserAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	private List<SimpleGrantedAuthority> getAdminAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
