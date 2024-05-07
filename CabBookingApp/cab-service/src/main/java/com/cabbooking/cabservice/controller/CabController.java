package com.cabbooking.cabservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.cabservice.entity.Cab;
import com.cabbooking.cabservice.service.CabService;

@RestController
@RequestMapping("/cab")
public class CabController {

	@Autowired
	private CabService cabService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cab> fetchCabDetails(@PathVariable("id") int cabId) {
		Cab cab = cabService.getCabById(cabId);
		return new ResponseEntity<>(cab, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<Cab> saveCab(@RequestBody Cab cab) {
		cabService.insertCab(cab);
		ResponseEntity<Cab> responseEntity = new ResponseEntity<>(cab, HttpStatus.CREATED);
		return responseEntity;
	}
	

	@PutMapping("/update")
	public ResponseEntity<Cab> editCab(@RequestBody Cab cab) {
		cabService.updateCab(cab);
		ResponseEntity<Cab> responseEntity = new ResponseEntity<>(cab, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCab(@PathVariable("id") int cabId) {
		String msg = "Deleted Seccessfully";
		cabService.deleteCab(cabId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/all")
	public List<Cab> fetchAllCabs() {
		List<Cab> cabs = cabService.getAllCabs();
		return cabs;
	}


	@GetMapping("/cabsCount")
	public String countCabsOfType() {
		int countCab = cabService.countCabsOfType();

		return "Number of Cabs Abvailable " + countCab;
	}

}