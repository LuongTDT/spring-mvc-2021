package com.salaryspringmvc.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	private static final Logger LOGGER = Logger.getLogger(MainController.class);

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
	public String logoutSuccessfulPage(ModelMap model) {
		model.addAttribute("title", "Logout");
		return "logout-successful";
	}

	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public String userInfo(ModelMap model, Principal principal) {
		// after login successful
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		model.addAttribute("message", userName);
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

	/*
	 * http://localhost:8080/study/user-requestparam sent data from a client such as
	 * FORM consist of the name of any field or any name as data from the client
	 */
	@RequestMapping(value = "/user-requestparam")
	public String userRequestParam(ModelMap model,
			@RequestParam(name = "username", required = false) String requestParamMessage) {
		requestParamMessage = (requestParamMessage != "" && requestParamMessage != null) ? requestParamMessage
				: "No data found!";
		LOGGER.info("Request-param: " + requestParamMessage);
		model.addAttribute("message", requestParamMessage);
		return "home";
	}

	// http://localhost:8080/study/user/15/Luong
	@RequestMapping(value = "/user/{id}/{name}")
	public String userPathVariable(ModelMap model, @PathVariable(name = "id") String id,
			@PathVariable(name = "name") String name) {
		id = (id != "" && id != null) ? id : "No data id found!";
		name = (name != "" && name != null) ? name : "No data name found!";

		model.addAttribute("message", name + id);
		return "home";
	}

	/*
	 * Download file {variable_name:regular_expression}
	 */
	@RequestMapping(value = "/download/{fileName:.+}")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "fileName") String fileName) {

		String sourceImagesDirectory = "D:/Personal/";
		String contentType = "image/*";
		String message = "Sorry! download failed!";
		ServletOutputStream outputStream = null;
		Path filePath = Paths.get(sourceImagesDirectory, fileName);
		boolean isFileDownloadExist = Files.exists(filePath);

		if (isFileDownloadExist) {
			response.setContentType(contentType);
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			try {
				outputStream = response.getOutputStream();
				Files.copy(filePath, outputStream);
				outputStream.flush();
				message = "File " + fileName + " has been downloaded successfully!";
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			message = "File not found: " + fileName;
		}
		System.out.println(message);
	}

}
