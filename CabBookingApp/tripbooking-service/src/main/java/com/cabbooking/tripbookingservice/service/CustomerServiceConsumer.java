package com.cabbooking.tripbookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.tripbookingservice.model.Customer;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceConsumer {
	
	@GetMapping("/customer/{id}")
	Customer fetchCustomerDetails(@PathVariable("id") int customerId);
	
	@PutMapping("/customer/update")
	Customer updateCustomer(@RequestBody Customer customer);
	

}
