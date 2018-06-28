package com.application.library.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.library.model.Category;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void whenFindByName_thenReturnCategory() {
		//given
		Category cat = new Category("linux");
		entityManager.persist(cat);
		entityManager.flush();
		
		// when
		Category found = categoryDao.findByCategoryName("linux");
		
		// then
		assertThat(found.getCategoryName()).isEqualTo("linux");
	}
}
