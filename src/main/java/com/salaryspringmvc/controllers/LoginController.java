package com.salaryspringmvc.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login-form";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {
		
		boolean	isCorrect = false;
		
		if(username != null && password != null) 
		isCorrect = (username.equals("admin") && password.equals("123")) ? true : false;
		
		if(isCorrect) {
			LOGGER.info("Welcome "+username+" login into system");
		} else {
			LOGGER.info("Sorry "+username+" login into system");
		}
		
		return "login-form";
	}
}
