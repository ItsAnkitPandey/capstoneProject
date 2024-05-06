package com.cabbooking.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.adminservice.model.Cab;

@FeignClient(name = "CAB-SERVICE")
public interface CabServiceConsumer {

	@GetMapping("/cab/{id}")
	Optional<Cab> fetchCabDetails(@PathVariable("id") int cabId);

	@PostMapping("/cab/insert")
	public Cab saveCab(@RequestBody Cab cab);

	@PutMapping("/cab/update")
	public Cab editCab(@RequestBody Cab cab);

	@DeleteMapping("/cab/{id}")
	public String deleteCab(@PathVariable("id") int cabId);

	@GetMapping("/cab/all")
	public List<Cab> fetchAllCabs();

	@GetMapping("/cab/cabsCount")
	public String countCabsOfType();

}
