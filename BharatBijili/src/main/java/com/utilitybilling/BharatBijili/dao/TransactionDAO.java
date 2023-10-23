package com.utilitybilling.BharatBijili.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.utilitybilling.BharatBijili.model.Customer;
import com.utilitybilling.BharatBijili.model.Transaction;

@Repository
public class TransactionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Optional<Transaction> findByTransactionId(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Transaction.class);
		criteria.add(Restrictions.eq("id", id));
		Transaction transaction = (Transaction) criteria.uniqueResult();
		session.close();
		return Optional.ofNullable(transaction);
	}
	
	public List<Transaction> getAll(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Transaction.class);
		return criteria.list();
	}
	
	public List<Transaction> findTransactionByCustomerId(Long customerId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Transaction.class);
	    criteria.createAlias("customer", "c");
	    criteria.add(Restrictions.eq("c.customerId", customerId)); 
		return criteria.list();
	}
	
	public void save(Transaction transaction) {
		Session session = sessionFactory.openSession();
		session.save(transaction);
		session.beginTransaction().commit();	
		session.close();
	}

}
