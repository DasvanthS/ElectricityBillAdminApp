package com.utilitybilling.BharatBijili.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(CustomerAlreadyFoundException.class)
	public ResponseEntity<String> handleCustomerAlreadyFoundException(CustomerAlreadyFoundException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(MonthlyBillAlreadyExistsException.class)
	public ResponseEntity<String> handleMonthlyBillAlreadyExistsException(MonthlyBillAlreadyExistsException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<String> handleInvalidOTPException(InvalidOTPException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(MonthlyBillNotFoundException.class)
	public ResponseEntity<String> handleMonthlyBillNotFoundException(MonthlyBillNotFoundException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(BillAlreadyPaidException.class)
	public ResponseEntity<String> handleBillAlreadyPaidException(BillAlreadyPaidException ex){
		logger.error(ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	
	
}
