package com.application.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.application.library.model.Book;
import com.application.library.model.Category;
import com.application.library.model.User;
import com.application.library.model.UserBook;
import com.application.library.service.BookService;
import com.application.library.service.CategoryService;
import com.application.library.service.UserBookService;
import com.application.library.service.UserService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	UserBookService userBookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	Date today = new Date();

	@Value("${book.dueDateCount}")
	private int dueDateCount;

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

	@RequestMapping(value = "/admin/add-new-book", method = RequestMethod.GET)
	public ModelAndView addBook() {

		ModelAndView model = new ModelAndView();
		model.addObject("categories", categoryService.listAllCategories());
		model.setViewName("newbookform");
		return model;

	}

	@RequestMapping(value = "/admin/add-new-book", method = RequestMethod.POST)
	public ModelAndView addBook2(Book book, Category cat) {

		bookService.addBook(book);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-all-books");
		return model;
	}

	@RequestMapping(value = "/admin/edit-book/{book_id}", method = RequestMethod.GET)
	public ModelAndView editBook(@PathVariable("book_id") Integer bookId) {
		Book book = bookService.findByBookId(bookId);
		List<Category> categories = categoryService.listAllCategories();

		ModelAndView model = new ModelAndView();
		model.addObject("book", book);
		model.addObject("categories", categories);
		model.setViewName("editbookform");
		return model;
	}

	@RequestMapping(value = "/admin/edit-book/{book_id}", method = RequestMethod.POST)
	public ModelAndView editBookpost(Book book) {
		bookService.addBook(book);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-all-books");
		return model;

	}

	@RequestMapping(value = "/admin/delete-book/{book_id}", method = RequestMethod.GET)
	public ModelAndView deleteBook(@PathVariable("book_id") Integer bookId) throws Exception {
		Book book = bookService.findByBookId(bookId);
		if (book == null) {
			throw new Exception("Exception occured, Book does not exists");
		}

		bookService.deleteBook(bookId);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-all-books");
		return model;
	}

	@RequestMapping(value = "/admin/issue-book", method = RequestMethod.GET)
	public ModelAndView issueBookForm() {

		ModelAndView model = new ModelAndView();
		model.addObject("allBooksList", bookService.listAllBooks());
		model.addObject("allUsersList", userService.listAllUsers());
		model.setViewName("issuebookform");
		return model;

	}

	@RequestMapping(value = "/admin/issue-book", method = RequestMethod.POST)
	public @ResponseBody ModelAndView issueBook(Book book, User user) {
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24 * dueDateCount));

		UserBook ub = new UserBook();
		ub.setBook(book);
		ub.setUser(user);
		ub.setIssue_date(today);
		ub.setReturn_due_date(tomorrow);
		ub.setFine(0);
		userBookService.issueBook(ub);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-issued-books");

		return model;
	}

	@RequestMapping(value = "/admin/list-all-books", method = RequestMethod.GET)
	public ModelAndView listAllBooks() {

		ModelAndView model = new ModelAndView();
		model.addObject("allBooksList", bookService.listAllBooks());
		model.setViewName("adminbooklist");
		return model;
	}

	@RequestMapping(value = "/admin/list-issued-books", method = RequestMethod.GET)
	public ModelAndView listAllIssuedBooks() {

		ModelAndView model = new ModelAndView();
		model.addObject("allIssuedBooksList", userBookService.getAllIssuedBooks());
		model.setViewName("viewallissuedbooks");
		return model;

	}

	@RequestMapping(value = "/admin/return-book/{id}", method = RequestMethod.GET)
	public ModelAndView returnBookForm(@PathVariable("id") Integer id) throws Exception {
		UserBook userBook = userBookService.findById(id);

		if (userBook == null) {
			throw new Exception("Exception occured, book issue entry does not exists");
		} else {
			userBook.setFine(userBookService.calculateFine(userBook));
		}

		ModelAndView model = new ModelAndView();
		model.addObject("bookToReturn", userBook);
		model.setViewName("returnbookform");
		return model;

	}

	@RequestMapping(value = "/admin/return-book/{id}", method = RequestMethod.POST)
	public ModelAndView returnBookFormSubmit(@PathVariable("id") Integer id) throws Exception {
		UserBook userBook = userBookService.findById(id);
		userBook.setActual_return_date(today);
		userBook.setFine(userBookService.calculateFine(userBook));

		userBookService.returnBook(userBook);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-issued-books");
		return model;

	}

	@RequestMapping(value = "/admin/search-book", method = RequestMethod.GET)
	public ModelAndView searchBook(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "category", required = false) String categoryId) {

		System.out.println(title + author + categoryId);
		ModelAndView model = new ModelAndView();
		model.addObject("categories", categoryService.listAllCategories());

		if ("-1".equals(categoryId))
			categoryId = null;
		if ("".equals(title))
			title = null;
		if ("".equals(author))
			author = null;

		if (title != null || author != null || categoryId != null) {
			model.addObject("allBooksList", bookService.searchBook(title, author, categoryId));
		} else {
			model.addObject("allBooksList", bookService.listAllBooks());

		}
		model.setViewName("searchbookform");
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {

		ModelAndView model = new ModelAndView();
		model.addObject("exception", ex);
		model.addObject("url", request.getRequestURL());
		model.setViewName("error");
		return model;
	}

	// Foll methods not used
	@RequestMapping(value = "/get-all-books-by-author/{author}", method = RequestMethod.GET)
	public List<Book> getAllBooksByAuthor(@PathVariable("author") String author) {
		return bookService.listAllBooksByAuthor(author);
	}

	@RequestMapping(value = "/get-all-books", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		return bookService.listAllBooks();
	}

	@RequestMapping(value = "/get-all-books-by-title/{title}", method = RequestMethod.GET)
	public List<Book> getAllBooksByTitle(@PathVariable("title") String title) {
		return bookService.listAllBooksByTitle(title);
	}
	
	@RequestMapping(value = "/admin/justtest", method = RequestMethod.GET)
	public String justfortest() {

		bookService.listAllBooks();
		return "sau";
	}

}
