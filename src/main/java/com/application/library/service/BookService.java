package com.application.library.service;

import java.util.List;

import com.application.library.model.Book;

public interface BookService {

	public boolean addBook(Book book);

	public boolean updateBook(Book book);

	public void deleteBook(Integer bookId);

	public List<Book> listAllBooks();

	public Book findByBookId(Integer bookId);

	public List<Book> listAllBooksByAuthor(String author);

	public List<Book> listAllBooksByTitle(String title);

	public List<Book> searchBook(String title, String author, String categoryId);

}
