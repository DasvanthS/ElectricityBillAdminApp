package com.utilitybilling.BharatBijili.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")})
public class Customer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private Long phoneNumber;	

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<MonthlyBill> monthlyBills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;;

	public Customer() {
		super();
	}

	public Customer(String name, String email, Long phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<MonthlyBill> getMonthlyBills() {
		return monthlyBills;
	}

	public void setMonthlyBills(List<MonthlyBill> monthlyBills) {
		this.monthlyBills = monthlyBills;
	}

	public void addMonthlyBill(MonthlyBill bill) {
		monthlyBills.add(bill);
	}
	
	public void removeMonthlyBill(MonthlyBill bill) {
		monthlyBills.remove(bill);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", monthlyBills=" + monthlyBills + ", transactions=" + transactions + "]";
	}
	
	

}
