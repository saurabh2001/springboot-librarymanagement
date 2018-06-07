package com.application.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.library.model.User;
import com.application.library.model.UserBook;
import com.application.library.service.BookService;
import com.application.library.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;

	@RequestMapping(value = "/admin/list-all-users", method = RequestMethod.GET)
	public ModelAndView listAllActiveUsers() {

		ModelAndView model = new ModelAndView();
		model.addObject("allUsersList", userService.listAllUsers());
		model.setViewName("adminuserlist");
		return model;

	}

	@RequestMapping(value = "/admin/list-active-users", method = RequestMethod.GET)
	public ModelAndView listAllUsers() {

		ModelAndView model = new ModelAndView();
		model.addObject("allUsersList", userService.listAllActiveUsers());
		model.setViewName("adminuserlist");
		return model;

	}

	@RequestMapping(value = "/admin/add-new-user", method = RequestMethod.GET)
	public ModelAndView addUser() {

		ModelAndView model = new ModelAndView();
		model.addObject("successMessage", "Please add user");
		model.setViewName("newuserform");
		return model;

	}

	@RequestMapping(value = "/admin/add-new-user", method = RequestMethod.POST)
	public @ResponseBody ModelAndView addUserPost(User user) {

		ModelAndView model = new ModelAndView();
		// book.setCategory(cat);
		model.setViewName("redirect:/admin/list-all-users");
		user.setActive(true);
		userService.addUser(user);
		return model;
		// return HttpStatus.OK;
	}

	@RequestMapping(value = "/admin/change-user-status/{userId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView makeActiveInactiveUser(@PathVariable("userId") Long userId) throws Exception {

		User user = userService.findByUserId(userId);
		if (user == null) {
			throw new Exception("Exception created by saurabh");
		}
		user.setActive(!user.isActive());
		userService.updateUser(user);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/admin/list-all-users");
		return model;
		// return HttpStatus.OK;
	}

	@RequestMapping(value = "/user/list-my-issued-books", method = RequestMethod.GET)
	public ModelAndView listRentedBooksToUser() {
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String loggedInUser = springUser.getUsername();

		User user = userService.findUserByEmail(loggedInUser);

		List<UserBook> u = user.getMyIssuedBooks();

		ModelAndView model = new ModelAndView();
		model.addObject("allRentedBookList", u);
		model.setViewName("userissuedbooklist");
		return model;

	}

	@RequestMapping(value = "/user/list-all-books", method = RequestMethod.GET)
	public ModelAndView listAllBooksToUser() {

		ModelAndView model = new ModelAndView();
		model.addObject("allBooksList", bookService.listAllBooks());
		model.addObject("isUser", "Yes");
		model.setViewName("adminbooklist");
		return model;

	}
}
