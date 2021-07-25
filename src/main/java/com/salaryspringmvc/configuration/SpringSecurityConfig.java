package com.salaryspringmvc.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.salaryspringmvc.authentication.myAuthenticationService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private myAuthenticationService authenticationService;
		
	@Autowired
	public void  configAuthentication(AuthenticationManagerBuilder authen) throws Exception {
		
		authen.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
		authen.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");
		
		authen.userDetailsService(authenticationService);
		/*
		authen.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password, enable from users where username=?")
		.authoritiesByUsernameQuery("select username, role from roles where username=?");
		 */
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	       http.csrf().disable();
	       //Do not requires login
	       http.authorizeRequests().antMatchers("/", "/home", "/login", "/logout").permitAll();
	       //Requires login 
	       http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");	 
	       // For ADMIN only.
	       http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	       // has no permission -> redirect to 403 page
	       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	       // Configure for Login Form.
	       http.authorizeRequests().and().formLogin()//
	               // Submit URL of login page
	               .loginProcessingUrl("/j_spring_security_check") // Submit URL
	               .loginPage("/login")//
	               .defaultSuccessUrl("/user-info")//
	               .failureUrl("/login?error=true")//
	               .usernameParameter("username")//
	               .passwordParameter("password")
	               //Configure for logout
	               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout-successful");
	}
}
