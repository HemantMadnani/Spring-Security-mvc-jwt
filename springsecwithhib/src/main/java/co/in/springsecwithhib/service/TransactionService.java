package co.in.springsecwithhib.service;

import java.util.List;

import co.in.springsecwithhib.model.Transaction;

public interface TransactionService {

	public List<Transaction> getAllTransactions();

	public Transaction getSingleTransaction(int id);

	public void addSingleTransaction(Transaction t);

}
