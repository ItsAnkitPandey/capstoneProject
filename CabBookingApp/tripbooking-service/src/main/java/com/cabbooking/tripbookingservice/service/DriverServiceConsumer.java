package com.cabbooking.tripbookingservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.tripbookingservice.model.Driver;
import com.cabbooking.tripbookingservice.model.DriverResponse;

@FeignClient(name="DRIVER-SERVICE")
public interface DriverServiceConsumer {
	
	@GetMapping("/driver/available")
	public List<DriverResponse> findByAvailable();

	@GetMapping("/driver/{id}")
	DriverResponse getDriverDetails(@PathVariable("id") int driverId);
	
	@PutMapping("driver/update")
	public Driver updateDriver(@RequestBody Driver driver);

}
