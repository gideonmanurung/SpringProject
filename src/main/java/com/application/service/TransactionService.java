package com.application.service;

import java.util.List;

import com.application.model.Transaction;

public interface TransactionService 
{
	public void saveOrUpdateTransaction(Transaction ts);
	
	public void deleteTransaction(int idTs);
	
	public Transaction getTransactionById(int idTs);
	
	public List<Transaction> getAllTransaction();
}
