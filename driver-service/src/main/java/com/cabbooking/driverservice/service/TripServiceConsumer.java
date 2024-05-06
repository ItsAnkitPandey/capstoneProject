package com.cabbooking.driverservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cabbooking.driverservice.model.TripResponse;




@FeignClient(name="TRIPBOOKING-SERVICE")
public interface TripServiceConsumer {
	
	@GetMapping("/trip/driver/{id}")
	public List<TripResponse> getTripsByDriverId(@PathVariable("id") int driverId);
	
	@GetMapping("/trip/{id}")
	public TripResponse getTripById(@PathVariable("id") int tripId);
	
	@GetMapping("/trip/calculatebill")
	public float calculateBill( int customerId);
	
}
