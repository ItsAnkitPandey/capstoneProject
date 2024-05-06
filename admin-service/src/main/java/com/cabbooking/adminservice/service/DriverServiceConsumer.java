package com.cabbooking.adminservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.adminservice.model.Driver;
import com.cabbooking.adminservice.model.DriverResponse;

import jakarta.validation.Valid;


@FeignClient(name="DRIVER-SERVICE")
public interface DriverServiceConsumer {
	
	@PostMapping("/driver/add")
	public Driver insertDriver(@Valid @RequestBody Driver driver);
	
	@PutMapping("/driver/update")
	public Driver updateDriver(@RequestBody Driver driver);
	
	@GetMapping("/driver/available")
	public List<DriverResponse> findByAvailable();

	@GetMapping("/driver/{id}")
	DriverResponse getDriverDetails(@PathVariable("id") int driverId);

	@GetMapping("/driver/all")
	public List<DriverResponse> fetchAllDriver() ;
	
	@DeleteMapping("/driver/{id}")
	public String deleteDriverById(@PathVariable("id") int driverId);
	
	@GetMapping("/driver/topDrivers")
	public List<DriverResponse> viewBestDrivers();

}
