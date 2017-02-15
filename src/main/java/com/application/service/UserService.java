package com.application.service;

import java.util.List;

import com.application.model.User;

public interface UserService 
{
	public void saveOrUpdateUser(User user);

	public void deleteUser(String username);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUser();
}
