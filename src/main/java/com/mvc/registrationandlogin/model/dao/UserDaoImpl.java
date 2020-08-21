package com.mvc.registrationandlogin.model.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mvc.registrationandlogin.model.User;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public UserDaoImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public int registerUser(User user) {

		String sql = "INSERT INTO Users (firstname, lastname, username, password) VALUES(?,?,?,?)";

		try {

			int counter = jdbcTemplate.update(sql, new Object[] { user.getFirstname(), user.getLastname(), user.getUserId(), user.getPassword() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String loginUser(User user) {

		String sql = "SELECT username FROM Users WHERE username=? AND password=?";

		try {

//			String userId = jdbcTemplate.queryForObject(sql, new Object[] {
//					user.getUserId(), user.getPassword() }, String.class);
//
//			return userId;
			List<String> users = jdbcTemplate.queryForList(sql, new Object[] { user.getUserId(), user.getPassword() },
					String.class);
			if (users.isEmpty()) {
				return null;
			} else {
				return users.get(0);
			}

		} catch (Exception e) {
			return null;
		}
	}
}