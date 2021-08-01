package com.salaryspringmvc.test;

import org.springframework.stereotype.Component;

@Component
public class EngSpeech implements Speech {

	@Override
	public String sayHello() {
		return "hello";
	}

}
