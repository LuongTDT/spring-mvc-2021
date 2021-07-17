package com.salaryspringmvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
			@RequestParam(name = "password", required = false) String password, ModelMap model, HttpServletRequest request) {
		String message ="";
		HttpSession session = request.getSession();
		
		boolean	isCorrect =(username != null && password != null) ?  ((username.equals("admin") && password.equals("123")) ? true : false) : false;	
		LOGGER.info("login infor => username: "+username+", password: "+password);
		if(!isCorrect) {
			message = "Login failed, please enter again!";
			LOGGER.error(message);
			model.addAttribute("message", message);
			return "login-form";
		}
		session.setAttribute("username",username);
		session.setMaxInactiveInterval(60*60*24);
		LOGGER.info("Welcome "+username+" login into system");
		return "redirect:home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null)session.removeAttribute("username");
		return "redirect:login";
	}
}
