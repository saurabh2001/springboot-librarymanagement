package com.application.library.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.library.model.Category;;
@Repository
public interface CategoryDao extends CrudRepository<Category, Integer>{

	Category findByCategoryName(String categoryName);
	
}
