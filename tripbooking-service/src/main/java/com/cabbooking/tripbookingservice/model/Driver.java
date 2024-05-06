package com.cabbooking.tripbookingservice.model;

public class Driver {
	private int userId;

	private String username;

	private String password;

	private Address address;

	private String mobile;

	private String email;

	private String licenseNo;

	private float rating;

	private boolean available;

	private int cabId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public Driver(int userId, String username, String password, Address address, String mobile, String email,
			String licenseNo, float rating, boolean available, int cabId) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.licenseNo = licenseNo;
		this.rating = rating;
		this.available = available;
		this.cabId = cabId;
	}

	public Driver() {
		super();
	}



}
