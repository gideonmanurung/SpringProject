package com.application.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.dao.TransactionDao;
import com.application.model.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao
{
	private EntityManagerFactory emf;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory emf)
	{
		this.emf = emf;
	}
		
	@Override
	public Transaction saveOrUpdateTransaction(Transaction ts)
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(ts);
		em.getTransaction().commit();
		Transaction trs = (Transaction)ts;
		return trs;
	}

	@Override
	public void deleteTransaction(int idTs) 
	{
		EntityManager em = emf.createEntityManager();
		Transaction ts = (Transaction)getTransactionById(idTs);
		em.getTransaction().begin();
		em.remove(ts);
		em.getTransaction().commit();
	}

	@Override
	public Transaction getTransactionById(int idTs) 
	{
		EntityManager em = emf.createEntityManager();
		return em.find(Transaction.class, idTs);
	}

	@Override
	public List<Transaction> getAllTransaction() 
	{
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Transaction" , Transaction.class).getResultList();
	}

}
