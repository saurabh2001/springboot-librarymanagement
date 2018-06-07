package com.application.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.library.model.Book;
@Repository
public interface BookDao extends CrudRepository<Book, Integer>{

	List<Book> findByAuthor(String author);
	List<Book> findByTitle(String title);
	
	@Query("select b from Book b where b.title like LOWER(CONCAT('%',:title, '%'))  "
			+ "or b.author like LOWER(CONCAT('%',:author, '%')) "
			+ "or b.category like LOWER(CONCAT('%',:categoryId, '%')) ")
	List<Book> searchBook(@Param("title") String title,
            @Param("author") String author, @Param("categoryId") String categoryId);
	
	/*@Query("select b from Book b where b.title like '%:title%' or b.author like '%:author%'")
	List<Book> searchBook(@Param("title") String title,
	                                 @Param("author") String author);*/
	
/*	@Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
    List<Book> fetchArticles(@Param("title") String title, @Param("category") String category);*/
}
