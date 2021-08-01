package com.salaryspringmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
	
	@Autowired
	VNSpeech speech;
	
	@RequestMapping("/hello")
	public String sayHello(ModelMap model) {
		model.addAttribute("message", speech.sayHello());
		return "home";
	}
}
