package co.in.springsecwithhib.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.in.springsecwithhib.dao.TransactionDao;
import co.in.springsecwithhib.model.Transaction;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getAllTransaction() {

		final Session session = sessionFactory.getCurrentSession();
		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		final CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
		final Root<Transaction> users = criteriaQuery.from(Transaction.class);
		criteriaQuery.select(users);
		final Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@Override
	public Transaction getSingleTransaction(final int id) {

		return sessionFactory.getCurrentSession().get(Transaction.class, id);
	}

	@Override
	public void addSingleTransaction(final Transaction t) {

		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

}
