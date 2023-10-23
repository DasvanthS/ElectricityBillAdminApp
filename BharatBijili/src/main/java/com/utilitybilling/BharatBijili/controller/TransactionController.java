package com.utilitybilling.BharatBijili.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utilitybilling.BharatBijili.model.Transaction;
import com.utilitybilling.BharatBijili.service.TransactionService;

@RestController
@RequestMapping("transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("getAllTransactions") //Get all transactions
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> transactions = transactionService.getAllTransacions();
		return ResponseEntity.status(HttpStatus.OK).body(transactions);
	}
	
	@GetMapping("getTransactionById/{id}") //Get transaction by transaction id
	public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
		Optional<Transaction> transaction = transactionService.getTransactionById(id);
		return ResponseEntity.status(HttpStatus.OK).body(transaction.get());
	}
	
	@GetMapping("getTransactionByCustomerId/{customerId}") //Get transactions by customer id
	public ResponseEntity<List<Transaction>> getTransactionByCustomerId(@PathVariable Long customerId){
		List<Transaction> transactions = transactionService.getTransactionByCustomerId(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(transactions);
	}

}
