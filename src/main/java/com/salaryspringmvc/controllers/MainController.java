package com.salaryspringmvc.controllers;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salaryspringmvc.utils.FileService;

@Controller
public class MainController {
	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "home";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		return "admin-page";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {

		return "login-form";
	}

	@RequestMapping(value = "/logout-successful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(ModelMap model, Principal principal) {
		model.addAttribute("title", "Logout");
		return "logout-successful";
	}

	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public String userInfo(ModelMap model, Principal principal) {
		// after login successful
		String username = principal.getName();
		model.addAttribute("message", username);
		model.addAttribute("title","User Information");
		return "user-info";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal principal) {
		if (principal != null) {
			model.addAttribute(
					"message", "Hi " + principal.getName() + "<br> You do not have permission to access this page!"
					);
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "errors/403";
	}
	//=================================================================================================================
	/*
	 * Download file {variable_name:regular_expression}
	 */
	@RequestMapping(value = "/download/{fileName:.+}")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "fileName") String fileName) {
		String sourceImagesDirectory = "D:/Personal/";
		String contentType = "image/*";
		String message = "File " + fileName + " has been downloaded successfully!";
		try {
			fileService.downloadFileFromServer(sourceImagesDirectory, fileName, contentType);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		LOGGER.info(message);
	}

}
