package com.application.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.library.model.User;
@Repository
public interface UserDao extends CrudRepository<User, Long>{

	 User findByUseremail(String useremail);
	
	 List<User> findByActiveTrue(); 
	 
}
