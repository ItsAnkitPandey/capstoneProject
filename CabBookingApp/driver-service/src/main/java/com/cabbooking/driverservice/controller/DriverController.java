package com.cabbooking.driverservice.controller;

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

import com.cabbooking.driverservice.entity.Driver;
import com.cabbooking.driverservice.model.DriverResponse;
import com.cabbooking.driverservice.model.TripResponse;
import com.cabbooking.driverservice.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/driver")
public class DriverController {
	@Autowired
	private DriverService driverService;

	@PostMapping("/add")
	public ResponseEntity<Driver> insertDriver(@Valid @RequestBody Driver driver) {
		Driver addedDriver = driverService.insertDriver(driver);
		return new ResponseEntity<Driver>(addedDriver, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DriverResponse> viewDriverById(@PathVariable("id") int driverId) {
		DriverResponse driver = driverService.viewDriverById(driverId);
		return new ResponseEntity<>(driver, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<DriverResponse> fetchAllDriver() {
		List<DriverResponse> drivers = driverService.getAllDrivers();
		return drivers;
	}

	@PutMapping("/update")
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {
		driverService.updateDriver(driver);
		ResponseEntity<Driver> responseEntity = new ResponseEntity<>(driver, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public String deleteDriverById(@PathVariable("id") int driverId) {
		return driverService.deleteDriverById(driverId);
	}

	@GetMapping("/topDrivers")
	public ResponseEntity<List<DriverResponse>> viewBestDrivers() {
	    List<DriverResponse> list = driverService.viewBestDriver();
	    return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<DriverResponse>> availableDrivers() {
	    List<DriverResponse> list = driverService.findByAvailabble();
	    return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/trip/history/{id}")
	public List<TripResponse> getTripsByDriverId(@PathVariable("id") int driverId){
		return driverService.getTripsByDriverId(driverId);
	}
	
	@GetMapping("/trip/detail/{id}")
	public TripResponse getTripById(@PathVariable("id") int tripId) {
		return driverService.getTripById(tripId);
	}
	
	@GetMapping("/trip/calculatebill")
	public float calculateBill( int customerId) {
		return driverService.calculateBill(customerId);
		
	}
}
