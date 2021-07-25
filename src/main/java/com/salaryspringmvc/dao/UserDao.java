package com.salaryspringmvc.dao;

import java.util.List;

import com.salaryspringmvc.models.UserInfo;

public interface UserDao {
	
	UserInfo findUser(String username);
	
	List<String> getUserRoles(String username);
	
}
