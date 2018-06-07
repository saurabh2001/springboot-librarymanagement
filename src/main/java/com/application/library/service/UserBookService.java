package com.application.library.service;

import java.text.ParseException;
import java.util.List;

import com.application.library.model.Book;
import com.application.library.model.User;
import com.application.library.model.UserBook;

public interface UserBookService {

	public UserBook findById(Integer id);
	
	public void returnBook(UserBook userBook);
	
	public List<UserBook> getAllIssuedBooks();
	
	public void issueBook(UserBook userBook);

	public Integer calculateFine(UserBook userBook) throws ParseException;
}
