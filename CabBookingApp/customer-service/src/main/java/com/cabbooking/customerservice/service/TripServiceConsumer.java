package com.cabbooking.customerservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.customerservice.model.TripBooking;
import com.cabbooking.customerservice.model.TripResponse;

import jakarta.validation.Valid;



@FeignClient(name="TRIPBOOKING-SERVICE")
public interface TripServiceConsumer {
	
	@GetMapping("/trip/customer/{id}")
	public List<TripResponse> getTripsByCustomerId(@PathVariable("id") int customerId);
	
	@PutMapping("/trip/canceltrip/{id}")
	public String cancel(@PathVariable("id") int id);
	
	@GetMapping("/trip/{id}")
	public TripResponse getTripById(@PathVariable("id") int tripId);
	
	@PostMapping("/trip/book")
	public TripBooking Book(@Valid @RequestBody TripBooking trip);
	
	@GetMapping("/trip/calculatebill")
	public float calculateBill( int customerId);
	
}
