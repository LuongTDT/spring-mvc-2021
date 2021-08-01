package com.salaryspringmvc.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.salaryspringmvc")
public class SpringConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
		driverManagerDataSource.setUrl(env.getProperty("dataSource.url"));
		driverManagerDataSource.setUsername(env.getProperty("dataSource.username"));
		driverManagerDataSource.setPassword(env.getProperty("dataSource.password"));
		
		return driverManagerDataSource;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {		  
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
