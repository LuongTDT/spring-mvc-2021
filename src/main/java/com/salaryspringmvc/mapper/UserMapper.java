package com.salaryspringmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.salaryspringmvc.models.UserInfo;

public class UserMapper implements RowMapper<UserInfo>{

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		String username = rs.getString("username");
		String password = rs.getString("password");
		return new UserInfo(username,password,"");
	}
}
