package com.cabbooking.tripbookingservice.model;


public class Customer {
	private int userId;
	private String username;
	private String password;
	private String mobile;
	private String email;
	private Address address;
	private boolean journey_status;
	
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isJourney_status() {
		return journey_status;
	}

	public void setJourney_status(boolean journey_status) {
		this.journey_status = journey_status;
	}
	
	

}
