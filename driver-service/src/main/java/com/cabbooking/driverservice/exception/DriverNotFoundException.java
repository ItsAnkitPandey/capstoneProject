package com.cabbooking.driverservice.exception;

public class DriverNotFoundException extends RuntimeException {
	public DriverNotFoundException() {

	}

	public DriverNotFoundException(String message) {
		super(message);
	}
}
