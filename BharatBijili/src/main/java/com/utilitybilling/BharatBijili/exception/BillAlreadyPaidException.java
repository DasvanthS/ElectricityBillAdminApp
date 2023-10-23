package com.utilitybilling.BharatBijili.exception;

public class BillAlreadyPaidException extends RuntimeException{
	public BillAlreadyPaidException(String message) {
		super(message);
	}
}
