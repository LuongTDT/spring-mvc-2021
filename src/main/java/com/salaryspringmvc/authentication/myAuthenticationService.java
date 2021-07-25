package com.salaryspringmvc.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salaryspringmvc.dao.UserDao;
import com.salaryspringmvc.models.UserInfo;

@Service
public class myAuthenticationService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userDao.findUser(username);
		
		BCryptPasswordEncoder encoder = passwordEncoder();
		
        if (userInfo == null) throw new UsernameNotFoundException("UserInfo " + username + " was not found in the database");
        
        List<String> roles= userDao.getUserRoles(username);
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        
        if(roles != null) {
        	for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
        	}
        }
        
        UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(),encoder.encode(userInfo.getPassword()),grantList);
        
		return userDetails;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
