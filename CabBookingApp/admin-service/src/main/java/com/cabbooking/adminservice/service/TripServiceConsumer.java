package com.cabbooking.adminservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cabbooking.adminservice.model.TripResponse;


@FeignClient(name="TRIPBOOKING-SERVICE")
public interface TripServiceConsumer {
	@GetMapping("/trip/all")
	public List<TripResponse> getAllTrips();
	
	@GetMapping("/trip/customer/{id}")
	public List<TripResponse> getTripsByCustomerId(@PathVariable("id") int customerId);
	
	@GetMapping("/trip/driver/{id}")
	public List<TripResponse> getTripsByDriverId(@PathVariable("id") int driverId);
	
	@DeleteMapping("/trip/delete/{id}")
	public String delete(@PathVariable("id") int id);
}
