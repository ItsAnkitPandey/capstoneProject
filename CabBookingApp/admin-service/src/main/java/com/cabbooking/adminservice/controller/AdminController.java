package com.cabbooking.adminservice.controller;

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

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.model.Cab;
import com.cabbooking.adminservice.model.Customer;
import com.cabbooking.adminservice.model.Driver;
import com.cabbooking.adminservice.model.DriverResponse;
import com.cabbooking.adminservice.model.TripResponse;
import com.cabbooking.adminservice.service.AdminService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/create")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {
		Admin savedAdmin = adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
		Admin updatedAdmin = adminService.updateAdmin(admin);
		return new ResponseEntity<String>("admin updated " + updatedAdmin, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteMappingHandler(@PathVariable("adminId") int adminId) {
		Admin admin = adminService.deleteAdmin(adminId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("/trips/all")
	public ResponseEntity<List<TripResponse>> getAllTrips() {

		List<TripResponse> trips = adminService.getAllTrips();
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	@GetMapping("/trips/driverwise/{id}")
	public ResponseEntity<List<TripResponse>> getTripsDriverwiseHandler(@PathVariable("id") int driverId) {

		List<TripResponse> trips = adminService.getTripsDriverwise(driverId);
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	

	@GetMapping("/trips/customertrips/{id}")
	public List<TripResponse> getTripsCustomerwiseHandler(@PathVariable("id") int customerId) {
		List<TripResponse> list = adminService.getTripsCustomerwise(customerId);
		return list;
	}
	
//	==================== CAB MANAGEMENT ======================
	
	@PostMapping("/cab/create")
	public Cab saveCab(@RequestBody Cab cab) {
		return adminService.insertCab(cab);
		
		
	}
	
	@PutMapping("/cab/update")
	public Cab editCab(@RequestBody Cab cab) {
		return adminService.updateCab(cab);
	}
	
	@DeleteMapping("/cab/{id}")
	public String deleteCab(@PathVariable("id") int cabId) {
		 return adminService.deleteCab(cabId);
	}
	
	@GetMapping("/cab/all")
	public List<Cab> fetchAllCabs() {
		return adminService.getAllCabs();
	}
	
	@GetMapping("/cab/cabsCount")
	public String countCabsOfType() {
		return adminService.countCabsOfType();
	}
	
	
//	====================== DRIVER MANAGEMENT ==========================
	
	@PostMapping("/driver/add")
	public Driver insertDriver(@Valid @RequestBody Driver driver) {
		return  adminService.insertDriver(driver);
	}
	
	@GetMapping("/driver/all")
	public List<DriverResponse> fetchAllDriver(){
		return adminService.getAllDrivers();
	}
	
	@PutMapping("/driver/update")
	public Driver updateDriver(@RequestBody Driver driver) {
		return adminService.updateDriver(driver);
	}
	
	@DeleteMapping("/driver/{id}")
	public String deleteDriverById(@PathVariable("id") int driverId) {
		return adminService.deleteDriverById(driverId);
	}
	
	@GetMapping("/driver/topDrivers")
	public List<DriverResponse> viewBestDrivers() {
		return adminService.viewBestDriver();
	}
	
	@GetMapping("/driver/available")
	public List<DriverResponse> availableDrivers() {
		return  adminService.findByAvailabble();
	}
	
//	================ Customer Controller =====================
	
	@GetMapping("/customer/all")
	public List<Customer> fetchAllCustomers() {
		 
		return adminService.allCustomer();
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable("id") int customerId) {
		return adminService.deleteCustomer(customerId);
	}
	

}