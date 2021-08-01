package com.salaryspringmvc.test;

import org.springframework.stereotype.Component;

@Component
public class VNSpeech implements Speech {
	
	@Override
	public String sayHello() {
		return "xin chao";
	}

}
