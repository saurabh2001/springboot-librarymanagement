package com.application.library.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.application.library.model.Book;
import com.application.library.model.Category;
import com.application.library.service.BookService;
import com.application.library.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

	// https://www.youtube.com/watch?v=Ijj5RLSAcYw
	// Techprimers vdo
	private MockMvc mockMvc;

	@Mock
	BookService bookService;

	@Mock
	CategoryService categoryService;

	@InjectMocks // @InjectMocks will inject all the @Mocks created
	BookController bookController;

	@Before
	public void setup() throws IOException {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).addFilters(new CorsFilter()).build();
	}

	@Test
	public void workingTestCase1() throws Exception {
		List<Category> mockCategories = new ArrayList<>();
		mockCategories.add(new Category("Category1"));
		mockCategories.add(new Category("Category2"));

		Mockito.when(categoryService.listAllCategories()).thenReturn(mockCategories);

		// here we are calling api means the controller, so we have to verify if
		// the category service(in this perticular api) got called from book
		// controller or not
		// that we are doing using Mokito.verify
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/add-new-book"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("categories", mockCategories))
				.andExpect(MockMvcResultMatchers.view().name("newbookform"));

		Mockito.verify(categoryService).listAllCategories();
	}

	@Test
	public void workingTestCase2() throws Exception {
		List<Category> mockCategories = new ArrayList<>();
		mockCategories.add(new Category("Category1"));
		mockCategories.add(new Category("Category2"));

		Book mockBook = new Book("angular", "joe", new Category("UI"));

		Mockito.when(categoryService.listAllCategories()).thenReturn(mockCategories);
		Mockito.when(bookService.findByBookId(4)).thenReturn(mockBook);

		// here we are calling api means the controller, so we have to verify if
		// the category service(in this perticular api) got called from book
		// controller or not
		// that we are doing using Mokito.verify
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/edit-book/4"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("categories", mockCategories))
				.andExpect(MockMvcResultMatchers.model().attribute("book", mockBook))
				.andExpect(MockMvcResultMatchers.view().name("editbookform"));

		Mockito.verify(categoryService).listAllCategories();
		Mockito.verify(bookService).findByBookId(4);
	}

	@Test
	public void workingTestCase3() throws Exception {
		List<Book> books = new ArrayList<>();
		Mockito.when(bookService.listAllBooks()).thenReturn(books);

		// here we are calling api means the controller, so we have to verify if
		// the book service(in this perticular api) got called from book
		// controller or not
		// that we are doing using Mokito.verify
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/list-all-books"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("allBooksList", books))
				.andExpect(MockMvcResultMatchers.view().name("adminbooklist"));

		Mockito.verify(bookService).listAllBooks();
	}

	@Test
	@Ignore
	public void workingTestCase6() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/justtest")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("sau"));

	}

	@Test
	@Ignore
	public void testwhenthen() throws Exception {
		Book book = new Book("angular", "joe", new Category("UI"));
		book.setBookId(1);

		Book book1 = new Book("mysql", "bing", new Category("DB"));
		book1.setBookId(2);

		List<Book> books = new ArrayList<>();
		books.add(book);
		books.add(book1);

		Mockito.when(bookService.listAllBooks()).thenReturn(books);

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/justtest")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("sau"));

		Mockito.verify(bookService).listAllBooks();
	}
}
