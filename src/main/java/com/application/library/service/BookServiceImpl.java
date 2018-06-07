package com.application.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.library.dao.BookDao;
import com.application.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao; 
	
	@Override
	public Book findByBookId(Integer bookId){
		return bookDao.findById(bookId).get();
	}
	
	@Override
	public synchronized boolean addBook(Book book) {
		
		bookDao.save(book);

		/*List<Book> books = bookDao.findByTitle(book.getTitle());
		if(!books.isEmpty())
		{
			//call update
			bookDao.save(book);
		}
		else{
			//calls save
			bookDao.save(book);
		}*/

		return true;
	}
	@Override
	public synchronized boolean updateBook(Book book) {
		List<Book> books = bookDao.findByTitle(book.getTitle());
		if(!books.isEmpty())
		{
			bookDao.save(book);
		}
		else{
			bookDao.save(book);
		}

		return true;
	}

	@Override
	public void deleteBook(Integer bookId) {
		bookDao.deleteById(bookId);

	}

	@Override
	public List<Book> listAllBooks() {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<Book>();
		bookDao.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<Book> listAllBooksByAuthor(String author) {
		return bookDao.findByAuthor(author);
	}
	
	@Override
	public List<Book> listAllBooksByTitle(String title) {
		return bookDao.findByTitle(title);
	}

	@Override
	public List<Book> searchBook(String title,String author, String categoryId){
		
		return bookDao.searchBook(title, author, categoryId);
	}
	
}