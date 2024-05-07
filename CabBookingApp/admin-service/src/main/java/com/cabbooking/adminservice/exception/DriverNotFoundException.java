package com.cabbooking.adminservice.exception;

public class DriverNotFoundException extends RuntimeException {
	public DriverNotFoundException() {

	}

	public DriverNotFoundException(String message) {
		super(message);
	}
}
