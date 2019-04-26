package co.in.springsecwithhib.dao;

import java.util.List;

import co.in.springsecwithhib.model.Transaction;

public interface TransactionDao {

	public List<Transaction> getAllTransaction();

	public Transaction getSingleTransaction(int id);

	public void addSingleTransaction(Transaction t);

}
