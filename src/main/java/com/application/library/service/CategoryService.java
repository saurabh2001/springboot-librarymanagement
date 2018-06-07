package com.application.library.service;

import java.util.List;

import com.application.library.model.Category;

public interface CategoryService {

	public List<Category> listAllCategories();
	public Category findByCategoryId(Integer categoryId);
	
	
	
}
