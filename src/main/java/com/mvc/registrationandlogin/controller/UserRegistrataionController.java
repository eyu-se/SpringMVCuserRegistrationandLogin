package com.mvc.registrationandlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.registrationandlogin.model.User;
import com.mvc.registrationandlogin.model.dao.UserDao;


@Controller
public class UserRegistrataionController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/registeration", method = RequestMethod.GET)
	public ModelAndView getRegistrationPage() {
		return new ModelAndView("registration");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView userRegistration(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,@RequestParam("userId") String userId,
			@RequestParam("password") String password) {

		ModelAndView mv = new ModelAndView();

		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUserId(userId);
		user.setPassword(password);

		int counter = userDao.registerUser(user);

		if (counter > 0) {
			mv.addObject("msg", "User registration successful.");
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("registration");

		return mv;

	}

}