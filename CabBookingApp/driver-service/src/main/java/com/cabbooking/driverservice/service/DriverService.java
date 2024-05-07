package com.cabbooking.driverservice.service;

import java.util.List;


import com.cabbooking.driverservice.entity.Driver;
import com.cabbooking.driverservice.exception.DriverNotFoundException;
import com.cabbooking.driverservice.model.DriverResponse;
import com.cabbooking.driverservice.model.TripResponse;


public interface DriverService {
	public Driver insertDriver(Driver driver);

	public DriverResponse viewDriverById(int driverId) throws DriverNotFoundException;
	
	List<DriverResponse> getAllDrivers();

	public Driver updateDriver(Driver driver) throws DriverNotFoundException;

	public String deleteDriverById(int id) throws DriverNotFoundException;

	public List<DriverResponse> viewBestDriver() ;
	
	public List<DriverResponse> findByAvailabble() ;
	
	public List<TripResponse> getTripsByDriverId( int driverId);
	
	public TripResponse getTripById(int tripId);
	
	public float calculateBill( int customerId);
	
}
