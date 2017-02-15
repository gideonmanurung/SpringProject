package com.application.dao;

import java.util.List;

import com.application.model.User;

public interface UserDao
{
	public User saveOrUpdateUser(User user);
	
	public void deleteUser(String username);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUser();
}
