package com.application.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.library.dao.CategoryDao;
import com.application.library.model.Category;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao catDao; 
	
	@Override
	public Category findByCategoryId(Integer categoryId){
		return catDao.findById(categoryId).get();
	}

	@Override
	public List<Category> listAllCategories() {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		catDao.findAll().forEach(e -> list.add(e));
		return list;
	}

}
