package com.mvc.registrationandlogin.model.dao;

import com.mvc.registrationandlogin.model.User;

public interface UserDao {

	public int registerUser(User user);

	public String loginUser(User user);

}