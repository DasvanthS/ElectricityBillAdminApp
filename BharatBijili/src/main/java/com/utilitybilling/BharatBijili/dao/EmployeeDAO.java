package com.utilitybilling.BharatBijili.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.utilitybilling.BharatBijili.model.Employee;

@Repository
public class EmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Optional<Employee> findEmployeeById(Long employeeId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) criteria.uniqueResult();
		session.close();
		return Optional.ofNullable(employee);
	}
	
	public String findEmployeeOtp(Long employeeId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) criteria.uniqueResult();
		String opt = employee.getOtp();
		session.close();
		return opt;
	}
	
	public void save(Employee employee) {
		Session session = sessionFactory.openSession();
		session.save(employee);
		session.beginTransaction().commit();	
		session.close();	}

	
	public Optional<Employee> findByEmployeeIdAndEmail(Long employeeId, String email) {
	    Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(Employee.class);
	    criteria.add(Restrictions.eq("employeeId", employeeId));
	    criteria.add(Restrictions.eq("email", email));
	    Employee employee = (Employee) criteria.uniqueResult();
	    session.close();    
	    return Optional.ofNullable(employee);
	}
	
	public void deleteOtp(LocalDateTime currentTime) {
		Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(Employee.class);
	    criteria.add(Restrictions.lt("otpExpiry", currentTime));
	    List<Employee> optExpiryList = criteria.list();
	    for(Employee employee : optExpiryList) {
	    	employee.setOtp(null);
	    	employee.setOtpExpiry(null);
	    	session.update(employee);
			session.beginTransaction().commit();
	    }
	}
	
	public void updateOtp(Long id, String otp, LocalDateTime time) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, id);
		employee.setOtp(otp);
		employee.setOtpExpiry(time);
		session.saveOrUpdate(employee);
		session.beginTransaction().commit();
		session.close();
	 }



}
