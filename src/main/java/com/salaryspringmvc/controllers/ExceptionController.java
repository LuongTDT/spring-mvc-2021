package com.salaryspringmvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ExceptionController {
	private static Logger logger = Logger.getLogger(ExceptionController.class);
	/*
	 * response status bad request with code 400
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {NoHandlerFoundException.class})
	public String exceptionHandler(Exception exception) {
		logger.error(exception.getMessage());
		return "404";
	}
	
	@ExceptionHandler(Exception.class)
	public String allExceptionHandler(Exception exception, HttpServletRequest request) {
		logger.error(exception.getMessage());
		request.setAttribute("message", exception.getMessage());
		return "error";
	}
	

}
