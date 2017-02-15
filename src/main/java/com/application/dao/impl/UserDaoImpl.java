package com.application.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.dao.UserDao;
import com.application.model.User;

@Repository
public class UserDaoImpl implements UserDao
{
	private EntityManagerFactory emf;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
	
	@Override
	public User saveOrUpdateUser(User user) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		User us = (User)user;
		return us;
	}

	@Override
	public void deleteUser(String username) 
	{
		EntityManager em = emf.createEntityManager();
		User user = (User)getUserByUsername(username);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}

	@Override
	public User getUserByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, username);
	}

	@Override
	public List<User> getAllUser() 
	{
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from User",User.class).getResultList();
	}

}
