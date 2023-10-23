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

@Repository
public class CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Optional<Customer> findByCustomerId(Long customerId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("customerId", customerId));
		Customer customer = (Customer) criteria.uniqueResult();
		session.close();
		return Optional.ofNullable(customer);
	}
	
	public Optional<Customer> findByEmail(String email) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("email", email));
		Customer customer = (Customer) criteria.uniqueResult();
		session.close();
		return Optional.ofNullable(customer);
	}
	
	public void save(Customer customer) {
		Session session = sessionFactory.openSession();
		session.save(customer);
		session.beginTransaction().commit();	
		session.close();
	}
	
	public List<Customer> getAll(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		return criteria.list();
	}

}
