package com.salaryspringmvc.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.salaryspringmvc.models.Person;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.salaryspringmvc")
public class SpringConfig implements WebMvcConfigurer {
	@Bean
	public ViewResolver viewResolver() {
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(prefix);
		viewResolver.setSuffix(suffix);

		return viewResolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	}
	
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		/*
		 * configure for gmail
		 */
		String hostMail = "smtp.gmail.com";
		int portMail = 587;
		String userName = "tdtl.dracula@gmail.com";
		String password = "****";
		
		mailSender.setHost(hostMail);
		mailSender.setPort(portMail);
		mailSender.setUsername(userName);
		mailSender.setPassword(password);
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.transport.protocol", "smtp");
		mailProperties.put("mail.debug", "true"); //print out everything on screen
		
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}
	
	@Bean(name = "person" ,initMethod = "init", destroyMethod = "destroy")
	public Person person() {
		Person person = new Person("Luong Tran");
		return person;
	}

}
