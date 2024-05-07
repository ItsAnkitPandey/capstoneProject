package com.cabbooking.adminservice.service;


import java.util.List;

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.exception.AdminExceptions;
import com.cabbooking.adminservice.exception.DriverNotFoundException;
import com.cabbooking.adminservice.exception.NotFoundException;
import com.cabbooking.adminservice.model.Cab;
import com.cabbooking.adminservice.model.Customer;
import com.cabbooking.adminservice.model.Driver;
import com.cabbooking.adminservice.model.DriverResponse;
import com.cabbooking.adminservice.model.TripResponse;


public interface AdminService {

	public Admin createAdmin(Admin admin) ;
	
	public Admin updateAdmin(Admin admin) throws AdminExceptions;
	
	public Admin deleteAdmin(int id) throws AdminExceptions;

	public List<TripResponse> getAllTrips() ;
	
	public List<TripResponse> getTripsDriverwise(int driverId);

	public List<TripResponse> getTripsCustomerwise(int customerId);
	
	//for managing cabs
	
	public Cab insertCab(Cab cab);

	public Cab updateCab(Cab cab) throws NotFoundException;

	public Cab getCabById(int cabId) throws NotFoundException;

	public String deleteCab(int cabId);

	List<Cab> getAllCabs();

	public String countCabsOfType();
	
	//for managing drivers
	
	public Driver insertDriver(Driver driver);

	public DriverResponse viewDriverById(int driverId) throws DriverNotFoundException;
	
	List<DriverResponse> getAllDrivers();

	public Driver updateDriver(Driver driver) throws DriverNotFoundException;

	public String deleteDriverById(int id) throws DriverNotFoundException;

	public List<DriverResponse> viewBestDriver() throws DriverNotFoundException;
	
	public List<DriverResponse> findByAvailabble() throws DriverNotFoundException;
	
	//for managing customers
	
	public String deleteCustomer(int id) throws NotFoundException;

	public List<Customer> allCustomer() ;
	
	public Customer findCustomer(int customerId);;


}
