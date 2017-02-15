package com.application.dao;

import java.util.List;

import com.application.model.Transaction;

public interface TransactionDao 
{
	public Transaction saveOrUpdateTransaction(Transaction ts);
	
	public void deleteTransaction(int idTs);
	
	public Transaction getTransactionById(int idTs);
	
	public List<Transaction> getAllTransaction();
}
