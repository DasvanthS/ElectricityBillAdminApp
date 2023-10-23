package com.utilitybilling.BharatBijili.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.utilitybilling.BharatBijili.dao.EmployeeDAO;
import com.utilitybilling.BharatBijili.exception.EmployeeNotFoundException;
import com.utilitybilling.BharatBijili.exception.InvalidOTPException;
import com.utilitybilling.BharatBijili.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public Employee validateEmployee(Long id, String email) {
		Optional<Employee> existingEmployee = employeeDAO.findByEmployeeIdAndEmail(id, email);

		if(existingEmployee.isPresent()) {
	        Employee employee = existingEmployee.get();
	        String otp = generateOTP();
	        LocalDateTime time = LocalDateTime.now().plusSeconds(180);
	        employee.setOtp(otp);
	        employee.setOtpExpiry(time);
	        employeeDAO.updateOtp(employee.getEmployeeId(), otp, time);
	        return employee;
		}
		throw new EmployeeNotFoundException("Employee not found");
    }
	
	public String generateOTP() {
		Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i=0; i<6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
	
	@Scheduled(fixedDelay = 180000)
	public void cleanExpiredOtp() {
		LocalDateTime currentTime = LocalDateTime.now();
		employeeDAO.deleteOtp(currentTime);
	}
	
	public Employee validateOtp(Long id, String otp) {
		Optional<Employee> existingEmployee = employeeDAO.findEmployeeById(id);
		if(existingEmployee.isPresent()) {
			Employee employee = existingEmployee.get();
			if(employee.getOtp().equals(otp)) {
				return employee;
			}
		}
		throw new InvalidOTPException("Invalid OTP");
	}
	
}
