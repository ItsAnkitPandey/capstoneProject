package com.cabbooking.customerservice.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.exception.IdInvalid;
import com.cabbooking.customerservice.exception.NullException;
import com.cabbooking.customerservice.model.TripBooking;
import com.cabbooking.customerservice.model.TripResponse;

import jakarta.validation.Valid;



public interface CustomerService {

	public Customer saveCustomer(Customer customer);

	public Customer findCustomer(int customerId) throws IdInvalid;

	public Customer updateCustomer(Customer customer) throws IdInvalid;

	public String deleteCustomer(int id) throws IdInvalid;

	public List<Customer> allCustomer() throws NullException;
	
	public TripResponse getTripById(int tripId);

	public Customer vaildCustomer(String Email, String Password) throws IdInvalid;
	
	public TripBooking Book(@Valid @RequestBody TripBooking trip);
	
	public String cancel( int customerId);
	
	public List<TripResponse> getTripsByCustomerId( int customerId);
	
	public float calculateBill( int customerId);



}
