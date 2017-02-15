package com.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.UserDao;
import com.application.model.User;
import com.application.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveOrUpdateUser(User user) 
	{
		userDao.saveOrUpdateUser(user);
	}

	@Override
	public void deleteUser(String username) {
		userDao.deleteUser(username);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userDao.getAllUser();
		return users;
	}

}
