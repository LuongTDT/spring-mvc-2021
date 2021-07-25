package com.salaryspringmvc.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.salaryspringmvc.dao.UserDao;
import com.salaryspringmvc.mapper.UserMapper;
import com.salaryspringmvc.models.UserInfo;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public UserInfo findUser(String username) {
		String sql = "Select username, password from users where username = ? ";

		Object[] params = new Object[] { username };
		
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, new UserMapper());
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> getUserRoles(String username) {
		String sql = "Select role from roles where username = ? ";

		Object[] params = new Object[] { username };

		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return roles;
	}

}
