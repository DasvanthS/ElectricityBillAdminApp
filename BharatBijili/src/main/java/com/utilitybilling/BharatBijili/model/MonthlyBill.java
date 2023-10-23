package com.utilitybilling.BharatBijili.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class MonthlyBill{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
    @JsonIgnore
	@OneToOne(mappedBy = "monthlyBill")
    private Transaction transaction;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private LocalDate dueDate;
    
    @Column(nullable = false)
    private double unitConsumption;

    @Column(nullable = false)
    private double amount;
    
    private double onlinePayDisAmt;
    
    private double dueDateDisAmt;
    
    @Column(nullable = false)
    private boolean paid;

	public MonthlyBill() {
		super();
	}

	public MonthlyBill(String month, LocalDate dueDate, double unitConsumption) {
		super();
		this.month = month;
		this.dueDate = dueDate;
		this.unitConsumption = unitConsumption;
		this.paid = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public double getUnitConsumption() {
		return unitConsumption;
	}

	public void setUnitConsumption(double unitConsumption) {
		this.unitConsumption = unitConsumption;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public double getOnlinePayDisAmt() {
		return onlinePayDisAmt;
	}

	public void setOnlinePayDisAmt(double onlinePayDisAmt) {
		this.onlinePayDisAmt = onlinePayDisAmt;
	}

	public double getDueDateDisAmt() {
		return dueDateDisAmt;
	}

	public void setDueDateDisAmt(double dueDateDisAmt) {
		this.dueDateDisAmt = dueDateDisAmt;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "MonthlyBill [id=" + id + ", customer=" + customer + ", transaction=" + transaction + ", month=" + month
				+ ", dueDate=" + dueDate + ", unitConsumption=" + unitConsumption + ", amount=" + amount
				+ ", onlinePayDisAmt=" + onlinePayDisAmt + ", dueDateDisAmt=" + dueDateDisAmt + ", paid=" + paid + "]";
	}

	
	
	

}
