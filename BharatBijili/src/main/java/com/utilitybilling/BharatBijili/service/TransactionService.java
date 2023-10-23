package com.utilitybilling.BharatBijili.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utilitybilling.BharatBijili.dao.TransactionDAO;
import com.utilitybilling.BharatBijili.exception.CustomerNotFoundException;
import com.utilitybilling.BharatBijili.exception.TransactionNotFoundException;
import com.utilitybilling.BharatBijili.model.Customer;
import com.utilitybilling.BharatBijili.model.Transaction;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private CustomerService customerService;
	
	public List<Transaction> getAllTransacions(){
		return transactionDAO.getAll();
	}
	
	public Optional<Transaction> getTransactionById(Long id) {
		Optional<Transaction> existingTransaction = transactionDAO.findByTransactionId(id);
		if(existingTransaction.isPresent()) {
	        return Optional.ofNullable(existingTransaction.get());
		}
		throw new TransactionNotFoundException("Transaction not found");
    } 
	
	public List<Transaction> getTransactionByCustomerId(Long customerId) {
		Optional<Customer> customer = customerService.getCustomerById(customerId);

		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		
		return transactionDAO.findTransactionByCustomerId(customerId);
    } 

}
