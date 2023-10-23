package com.utilitybilling.BharatBijili.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.utilitybilling.BharatBijili.model.MonthlyBill;

@Repository
public class MonthlyBillDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(MonthlyBill monthlyBill) {
		Session session = sessionFactory.openSession();
		session.save(monthlyBill);
		session.beginTransaction().commit();	
		session.close();
	}
	
	public List<MonthlyBill> getAll(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MonthlyBill.class);
		return criteria.list();
	}	
	
	public List<MonthlyBill> findBillByCustomerId(Long customerId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MonthlyBill.class);
	    criteria.createAlias("customer", "c");
	    criteria.add(Restrictions.eq("c.customerId", customerId)); 
		return criteria.list();
	}
	
	public Optional<MonthlyBill> findBillByBillId(Long billId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MonthlyBill.class);
	    criteria.add(Restrictions.eq("id", billId)); 
	    MonthlyBill monthlyBill = (MonthlyBill) criteria.uniqueResult();
	    return Optional.ofNullable(monthlyBill);
	}
	
	public Optional<MonthlyBill> findBillByCustomerIdAndMonth(Long customerId, String month) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MonthlyBill.class);
	    criteria.createAlias("customer", "c");
	    criteria.add(Restrictions.eq("c.customerId", customerId)); 
	    criteria.add(Restrictions.eq("month".toLowerCase(), month.toLowerCase()));
	    MonthlyBill monthlyBill = (MonthlyBill) criteria.uniqueResult();
	    return Optional.ofNullable(monthlyBill);
	}
	
	public void updateBillPaid(Long billId) {
		Session session = sessionFactory.openSession();
		MonthlyBill bill = session.get(MonthlyBill.class, billId);
		bill.setPaid(true);
		session.update(bill);
		session.beginTransaction().commit();
		session.close();
	 }

}
