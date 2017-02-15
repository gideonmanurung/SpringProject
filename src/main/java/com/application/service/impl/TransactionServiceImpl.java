package com.application.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.TransactionDao;
import com.application.model.Transaction;
import com.application.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService
{
	@Autowired
	private TransactionDao tDao;
	
	@Override
	public void saveOrUpdateTransaction(Transaction ts) 
	{
		tDao.saveOrUpdateTransaction(ts);
	}

	@Override
	public void deleteTransaction(int idTs)
	{
		tDao.deleteTransaction(idTs);
	}

	@Override
	public Transaction getTransactionById(int idTs) 
	{
		return tDao.getTransactionById(idTs);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		return tDao.getAllTransaction();
	}

}
