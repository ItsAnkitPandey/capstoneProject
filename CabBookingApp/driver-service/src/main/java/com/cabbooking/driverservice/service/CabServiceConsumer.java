package com.cabbooking.driverservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cabbooking.driverservice.model.Cab;


@FeignClient(name="CAB-SERVICE")
public interface CabServiceConsumer {
	
	@GetMapping("/cab/{id}")
	Cab fetchCabDetails(@PathVariable("id") int cabId);

}
