package com.salaryspringmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
	
	@Bean(name = "person" ,initMethod = "init", destroyMethod = "destroy")
	public Person person() {
		Person person = new Person("Luong Tran");
		return person;
	}

}
