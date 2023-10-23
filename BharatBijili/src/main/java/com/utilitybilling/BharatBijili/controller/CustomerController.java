package com.utilitybilling.BharatBijili.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utilitybilling.BharatBijili.model.Customer;
import com.utilitybilling.BharatBijili.model.MonthlyBill;
import com.utilitybilling.BharatBijili.service.CustomerService;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("addCustomer") // Add new Customer
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		customerService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@GetMapping("getCustomerById/{customerId}") // Get Customer by Id
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId){
		Customer customer = customerService.getCustomerById(customerId).get();
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@GetMapping("getAllCustomers") // Get all Customers
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}
	
	@GetMapping("getCustomerMonthlyBills/{customerId}") // Get MonthlyBills of Customer
	public ResponseEntity<List<MonthlyBill>> getMonthlyBillsByCustomerId(@PathVariable Long customerId){
		List<MonthlyBill> monthlyBills = customerService.getMonthlyBillsByCustomerId(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(monthlyBills);
	}

}
