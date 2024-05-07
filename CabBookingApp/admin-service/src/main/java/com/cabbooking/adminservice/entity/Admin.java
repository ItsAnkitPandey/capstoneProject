package com.cabbooking.adminservice.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int adminId;

	@NotNull(message = "Cannot be Null")
	private String username;
	
	@Size(min = 4, max = 20, message = "password length should be between 4 to 20")
	@NotNull(message = "Cannot be Null")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addrId")
	private Address address;
	
	@Size(min = 10, message = "mobile no should be of 10 digits")
	private String mobile;
	
	@Email
	@NotNull(message = "Cannot be Null")
	private String email;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	
	
}
