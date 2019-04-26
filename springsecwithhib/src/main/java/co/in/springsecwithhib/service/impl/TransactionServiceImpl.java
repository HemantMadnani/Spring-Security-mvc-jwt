package co.in.springsecwithhib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.springsecwithhib.dao.TransactionDao;
import co.in.springsecwithhib.model.Transaction;
import co.in.springsecwithhib.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Override
	@Transactional
	public List<Transaction> getAllTransactions() {

		return transactionDao.getAllTransaction();
	}

	@Override
	@Transactional
	public Transaction getSingleTransaction(final int id) {

		return transactionDao.getSingleTransaction(id);
	}

	@Override
	@Transactional
	public void addSingleTransaction(final Transaction t) {

		transactionDao.addSingleTransaction(t);

	}

}
