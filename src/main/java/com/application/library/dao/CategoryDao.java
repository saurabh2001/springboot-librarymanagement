package com.application.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.library.model.Category;;
@Repository
public interface CategoryDao extends CrudRepository<Category, Integer>{

	Category findByCategoryName(String categoryName);
	
/*	@Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
    List<Book> fetchArticles(@Param("title") String title, @Param("category") String category);*/
}
