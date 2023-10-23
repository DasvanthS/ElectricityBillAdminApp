package com.utilitybilling.BharatBijili.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.utilitybilling.BharatBijili.dao.MonthlyBillDAO;
import com.utilitybilling.BharatBijili.dao.TransactionDAO;
import com.utilitybilling.BharatBijili.exception.BillAlreadyPaidException;
import com.utilitybilling.BharatBijili.exception.CustomerNotFoundException;
import com.utilitybilling.BharatBijili.exception.MonthlyBillAlreadyExistsException;
import com.utilitybilling.BharatBijili.exception.MonthlyBillNotFoundException;
import com.utilitybilling.BharatBijili.model.Customer;
import com.utilitybilling.BharatBijili.model.MonthlyBill;
import com.utilitybilling.BharatBijili.model.PaymentMethod;
import com.utilitybilling.BharatBijili.model.Transaction;

@Service
public class MonthlyBillService {
	
	private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerService customerService;
	
	private MonthlyBillDAO monthlybillDAO;
	
	private TransactionDAO transactionDAO;
	
	@Autowired
	public MonthlyBillService(MonthlyBillDAO monthlybillDAO, CustomerService customerService, TransactionDAO transactionDAO) {
		this.monthlybillDAO = monthlybillDAO;
		this.customerService = customerService;
		this.transactionDAO = transactionDAO;
	}
	
	public MonthlyBill addMonthlyBill(MonthlyBill monthlyBill, Long customerId) {
		Optional<Customer> customer = customerService.getCustomerById(customerId);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found");
		}
		String month = monthlyBill.getMonth();
		
		if (!monthlybillDAO.findBillByCustomerIdAndMonth(customerId, month).isEmpty()) {
            throw new MonthlyBillAlreadyExistsException("Monthly bill  already exists.");
        }
		
		monthlybillDAO.save(createMonthlyBill(monthlyBill, customer.get()));
		return monthlyBill;
	}
	
	public List<MonthlyBill> getAllMonthlyBill(){
		return monthlybillDAO.getAll();
	}
	
	public List<String> uploadMonthlyBills(MultipartFile file) throws IOException {
	    List<String> bugList = new ArrayList<>();
	    
	    InputStream inputStream = file.getInputStream();
	    CsvParserSettings settings = new CsvParserSettings();
	    settings.setHeaderExtractionEnabled(true);
	    CsvParser parser = new CsvParser(settings);
	    List<Record> parseAllRecords = parser.parseAllRecords(inputStream);

        for (Record record : parseAllRecords) {
            String sno = record.getString("SNo");
            Long customerId = Long.parseLong(record.getString("customerId"));
            String month = record.getString("month");

        	try {
        	
	            Customer customer = customerService.getCustomerById(customerId).orElseThrow(() -> new CustomerNotFoundException("S.No " + sno + " - Customer Id " + customerId + " is Invalid. Please update the csv"));
	
	            if (!monthlybillDAO.findBillByCustomerIdAndMonth(customerId, month).isEmpty()) {
	                throw new MonthlyBillAlreadyExistsException("S.No " + sno + " - Monthly bill for " + month + " for customer " + customerId + " already exists. Please update the csv.");
	            }
	
	            MonthlyBill monthlybill = new MonthlyBill();
	            monthlybill.setMonth(record.getString("month"));
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            monthlybill.setDueDate(LocalDate.parse(record.getString("dueDate"), dateFormatter));
	            monthlybill.setUnitConsumption(Double.parseDouble(record.getString("unitConsumption")));     
	            
	            monthlybillDAO.save(createMonthlyBill(monthlybill, customer));
	
        	}catch(MonthlyBillAlreadyExistsException e) {
        		bugList.add(e.getMessage());
        		logger.error(e.getMessage());
        	}
        	catch(CustomerNotFoundException e) {
        		String bug = "S.No " + sno + " - Customer Id " + customerId + " is Invalid. Please update the csv";
        		bugList.add(bug);
        		logger.error(bug);
        	}
        }

        logger.info("CSV uploaded");
	    return bugList;
	}
	
	public MonthlyBill createMonthlyBill(MonthlyBill monthlyBill, Customer customer) {
		
		monthlyBill.setCustomer(customer);
		double amount = monthlyBill.getUnitConsumption() * 41.50;
		monthlyBill.setAmount(amount);
		monthlyBill.setOnlinePayDisAmt((amount * 5) / 100);
		monthlyBill.setDueDateDisAmt((amount * 5) / 100);
		
		return monthlyBill;

	}
	
	public MonthlyBill getBillById(Long billId) {
		Optional<MonthlyBill> bill = monthlybillDAO.findBillByBillId(billId);
		if(bill.isPresent()) {
			return bill.get();
		}
		throw new MonthlyBillNotFoundException("Monthly Bill not found");
	}
	
	public Transaction updateCashPayment(Long billId) {
		Optional<MonthlyBill> monthlyBill = monthlybillDAO.findBillByBillId(billId);

		if(monthlyBill.isPresent()) {
			MonthlyBill bill = monthlyBill.get();
			if(bill.isPaid()) {
				throw new BillAlreadyPaidException("Bill already paid");
			}
			Customer customer = customerService.getCustomerById(bill.getCustomer().getCustomerId()).get();

			Transaction transaction = new Transaction();
			if(LocalDate.now().isBefore(bill.getDueDate())) {
				transaction.setAmount(bill.getAmount() - bill.getDueDateDisAmt());
			}
			else {
				transaction.setAmount(bill.getAmount());
			}
			transaction.setPaymentDate(LocalDate.now());
			transaction.setCustomer(customer);
			transaction.setPaymentMethod(PaymentMethod.CASH);
			transaction.setMonthlyBill(bill);
			transactionDAO.save(transaction);
			monthlybillDAO.updateBillPaid(billId);
			return transaction;
		}
		throw new MonthlyBillNotFoundException("Monthly bill not found");
	}

}
