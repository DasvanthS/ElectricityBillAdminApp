package com.utilitybilling.BharatBijili.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utilitybilling.BharatBijili.model.Employee;
import com.utilitybilling.BharatBijili.service.EmployeeService;

@RestController
@RequestMapping("employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("login") //Employee login portal
	public ResponseEntity<Employee> employeeLogin(@RequestParam("id") Long id, @RequestParam("email") String email){
		Employee employee = employeeService.validateEmployee(id, email);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@GetMapping("login-otp") //Enter OTP
	public ResponseEntity<Employee> validateOtp(@RequestParam("id") Long id, @RequestParam("otp") String otp){
		Employee employee = employeeService.validateOtp(id, otp);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

}
