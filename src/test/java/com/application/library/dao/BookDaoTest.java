package com.application.library.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.library.model.Book;
import com.application.library.model.Category;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {

	@Autowired
	BookDao bookDao;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void whenFindByAuthor_thenReturnBookList() {
		//given
		Category cat  = new Category("UI");
		entityManager.persist(cat);
		entityManager.flush();
		
		Book book = new Book("angular", "joe", cat);
		Book book1 = new Book("mysql", "bing", cat);
		Book book2 = new Book("java", "joe", cat);
		
		entityManager.persist(book);
		entityManager.flush();
		
		entityManager.persist(book1);
		entityManager.flush();
		
		entityManager.persist(book2);
		entityManager.flush();
		
		// when
		List<Book> lBooks= bookDao.findByAuthor("joe");

		// then
		// as there are 2 books by author joe, the size of list should be 2
		org.junit.Assert.assertEquals(lBooks.size(), 2);
	}
}
