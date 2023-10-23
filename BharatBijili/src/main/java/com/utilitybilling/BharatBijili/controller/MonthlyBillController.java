package com.utilitybilling.BharatBijili.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utilitybilling.BharatBijili.model.MonthlyBill;
import com.utilitybilling.BharatBijili.model.Transaction;
import com.utilitybilling.BharatBijili.service.MonthlyBillService;

@RestController
@RequestMapping("monthlyBills")
@CrossOrigin(origins = "http://localhost:4200")
public class MonthlyBillController {
	
	@Autowired
	private MonthlyBillService monthlyBillService;
	
	@PostMapping("addMonthlyBill") //Add monthly bill for customer
	public ResponseEntity<MonthlyBill> addMonthlyBill(@RequestBody MonthlyBill monthlyBill, @RequestParam("id") Long customerId){
		monthlyBillService.addMonthlyBill(monthlyBill, customerId);
		return ResponseEntity.status(HttpStatus.OK).body(monthlyBill);
	}
	
	@GetMapping("getAllMonthlyBills") //Get all monthly bill
	public ResponseEntity<List<MonthlyBill>> getAllMonthlyBill(){
		List<MonthlyBill> monthlyBills = monthlyBillService.getAllMonthlyBill();
		return ResponseEntity.status(HttpStatus.OK).body(monthlyBills);
	}
	
	@PostMapping("uploadcsv") //Upload multiple monthlybills by CSV file
	public ResponseEntity<List<String>> uploadMonthlyBills(@RequestParam("file") MultipartFile file) throws IOException{
		List<String> bugList = monthlyBillService.uploadMonthlyBills(file);
		return ResponseEntity.status(HttpStatus.OK).body(bugList);
	}
	
	@PutMapping("updateCashPayment") // Update Monthly Bill of cash payments
	public ResponseEntity<Transaction> updateCashPayment(@RequestParam("billId") Long billId){
		Transaction transaction = monthlyBillService.updateCashPayment(billId);
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	@GetMapping("getAllMonthlyBillById/{billId}") //Get monthly bill by Id
	public ResponseEntity<MonthlyBill> getMonthlyBillById(@PathVariable Long billId){
		MonthlyBill monthlyBill = monthlyBillService.getBillById(billId);
		return ResponseEntity.status(HttpStatus.OK).body(monthlyBill);
	}
}
