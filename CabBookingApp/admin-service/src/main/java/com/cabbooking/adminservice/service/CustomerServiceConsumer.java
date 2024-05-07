package com.cabbooking.adminservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cabbooking.adminservice.model.Customer;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceConsumer {
	
	@GetMapping("/customer/{id}")
	Customer fetchCustomerDetails(@PathVariable("id") int customerId);
	
	@GetMapping("/customer/all")
	public List<Customer> fetchAllCustomers();
	

	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable("id") int customerId);

}
