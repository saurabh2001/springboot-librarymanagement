package com.application.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.library.model.UserBook;
@Repository
public interface UserBookDao extends CrudRepository<UserBook, Integer>{

	
}
