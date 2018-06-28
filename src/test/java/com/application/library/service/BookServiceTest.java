package com.application.library.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.library.dao.BookDao;
import com.application.library.model.Book;
import com.application.library.model.Category;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

	@Autowired
	BookService bookService;

	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public BookService bookService() {
            return new BookServiceImpl();
        }
    }
	
	@MockBean
	BookDao bookDao;
	
	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void workingTest1(){
		Book book = new Book("angular", "joe", new Category("UI"));
		book.setBookId(1);

		Book book1 = new Book("mysql", "bing", new Category("DB"));
		book1.setBookId(2);

		List<Book> mockBookList = new ArrayList<>();
		mockBookList.add(book);
		mockBookList.add(book1);
		Mockito.when(bookDao.findAll()).thenReturn(mockBookList);
		
		Assert.assertEquals(mockBookList, bookService.listAllBooks()); 
		Assert.assertEquals(new Integer(bookService.listAllBooks().size()), new Integer(2));
	}
	
	@Test
	public void workingTest2(){
		Book book = new Book("angular", "joe", new Category("UI"));
		book.setBookId(2);

		Mockito.when(bookDao.findById(new Integer(2))).thenReturn(Optional.of(book));
		
		Book retrivedbook = bookService.findByBookId(new Integer(2));
	    Assert.assertEquals(retrivedbook.getBookId(), new Integer(2));
	    Assert.assertEquals(retrivedbook.getTitle(), book.getTitle());
	    Assert.assertNotNull(retrivedbook);
	    
	}
	
}
