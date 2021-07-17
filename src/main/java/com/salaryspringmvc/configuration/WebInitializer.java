package com.salaryspringmvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		webCtx.register(SpringConfig.class);
		webCtx.setServletContext(servletContext);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		/*
		 * add encoding filter for all of request
		 */
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
		servletContext.addFilter("EncodingFilter", characterEncodingFilter)
		.addMappingForUrlPatterns(null, false, "/*");
	}

}
