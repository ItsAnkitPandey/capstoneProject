package com.cabbooking.customerservice.exception;

public class IdInvalid extends RuntimeException {

	public IdInvalid() {
		super();
	}
	
	public IdInvalid(String message) {
		super(message);
	}
}