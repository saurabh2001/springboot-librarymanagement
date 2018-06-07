package com.application.library.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.application.library.dao.UserBookDao;
import com.application.library.model.UserBook;

@Service
public class UserBookServiceImpl implements UserBookService {

	@Autowired
	private UserBookDao userbookDao;

	@Value("${lib.finePerDay}")
	private int finePerDay;
	
	@Override
	public UserBook findById(Integer id) {
		return userbookDao.findById(id).get();
	}

	@Override
	public void returnBook(UserBook userBook) {
		userbookDao.save(userBook);

	}

	@Override
	public List<UserBook> getAllIssuedBooks() {
		List<UserBook> list = new ArrayList<UserBook>();
		userbookDao.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public void issueBook(UserBook userBook) {
		userbookDao.save(userBook);
	}

	@Override
	public Integer calculateFine(UserBook userBook) throws ParseException {
		Integer fine = 0;
		Date dueDate = null;
		try {
			Date today = new Date();
			dueDate = userBook.getReturn_due_date();

			if (today.after(dueDate)) {
				
				long diffInMillies = Math.abs(today.getTime() - dueDate.getTime());
				Integer overdueDays = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				fine = overdueDays * finePerDay;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fine;
	}

}
