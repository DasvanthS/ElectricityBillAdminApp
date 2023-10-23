package com.utilitybilling.BharatBijili.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utilitybilling.BharatBijili.dao.CustomerDAO;
import com.utilitybilling.BharatBijili.dao.MonthlyBillDAO;
import com.utilitybilling.BharatBijili.exception.CustomerAlreadyFoundException;
import com.utilitybilling.BharatBijili.exception.CustomerNotFoundException;
import com.utilitybilling.BharatBijili.model.Customer;
import com.utilitybilling.BharatBijili.model.MonthlyBill;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private MonthlyBillDAO monthlyBillDAO;
	
	private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	public Customer addCustomer(Customer customer) {
		Optional<Customer> existingCustomerByEmail = customerDAO.findByEmail(customer.getEmail());

		if(existingCustomerByEmail.isEmpty()) {
	        customerDAO.save(customer);
	        logger.info("Customer added");
			return customer;
		}
		throw new CustomerAlreadyFoundException("Customer already found");
	}
	
	public Optional<Customer> getCustomerById(Long customerId) {
		Optional<Customer> existingCustomer = customerDAO.findByCustomerId(customerId);
		if(existingCustomer.isPresent()) {
	        return customerDAO.findByCustomerId(customerId);
		}
		throw new CustomerNotFoundException("Customer not found");
    } 
	
	public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
	
	public List<MonthlyBill> getMonthlyBillsByCustomerId(Long customerId){
		Optional<Customer> existingCustomer = customerDAO.findByCustomerId(customerId);
		if(existingCustomer.isPresent()) {
			return monthlyBillDAO.findBillByCustomerId(customerId);
		}
		throw new CustomerNotFoundException("Customer not found");

	}
	

}
