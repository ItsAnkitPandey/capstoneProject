package com.cabbooking.driverservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.driverservice.entity.Driver;
import com.cabbooking.driverservice.exception.DriverNotFoundException;
import com.cabbooking.driverservice.model.Cab;
import com.cabbooking.driverservice.model.DriverResponse;
import com.cabbooking.driverservice.model.TripResponse;
import com.cabbooking.driverservice.repository.AddressRepository;
import com.cabbooking.driverservice.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CabServiceConsumer cabService;
	
	@Autowired
	private TripServiceConsumer tripService;

	@Override
	public Driver insertDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public DriverResponse viewDriverById(int driverId) throws DriverNotFoundException {
		Optional<Driver> optionalDriver = driverRepository.findById(driverId);
		if (optionalDriver.isEmpty()) {
			throw new DriverNotFoundException("Driver not existing with id: " + driverId);
		}

		Driver driver = optionalDriver.get(); 
		DriverResponse driverResponse = new DriverResponse();
		driverResponse.setDriverId(driver.getUserId());
		driverResponse.setUsername(driver.getUsername());
		driverResponse.setAddress(driver.getAddress());
		driverResponse.setMobile(driver.getMobile());
		driverResponse.setEmail(driver.getEmail());
		driverResponse.setLicenseNo(driver.getLicenseNo());
		driverResponse.setRating(driver.getRating());
		driverResponse.setCabId(driver.getCabId());

		int cabId = driver.getCabId();

		Cab cab = cabService.fetchCabDetails(cabId);

		driverResponse.setCab(cab);

		return driverResponse;
	}

	@Override
	public Driver updateDriver(Driver driver) throws DriverNotFoundException {
		Optional<Driver> optionalDriver = driverRepository.findById(driver.getUserId());
		if (optionalDriver.isEmpty()) {
			throw new DriverNotFoundException("Driver not found with id: "+ driver.getUserId());
		}
		driverRepository.save(driver);
		return driver;
	}

	@Override
	public String deleteDriverById(int id) throws DriverNotFoundException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("No Driver found"));

		addressRepository.delete(driver.getAddress());
		driverRepository.delete(driver);

		return "Driver Id " + id + " deleted successfully ";
	}

	@Override
	public List<DriverResponse> viewBestDriver() throws DriverNotFoundException {
		List<Driver> drivers = driverRepository.viewBestDriver();
		if (!drivers.isEmpty()) {
			List<DriverResponse> driverResponseList = new ArrayList<>();
			for (Driver driver : drivers) {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setDriverId(driver.getUserId());
				driverResponse.setUsername(driver.getUsername());
				driverResponse.setAddress(driver.getAddress());
				driverResponse.setMobile(driver.getMobile());
				driverResponse.setEmail(driver.getEmail());
				driverResponse.setAvailable(driver.isAvailable());
				driverResponse.setLicenseNo(driver.getLicenseNo());
				driverResponse.setRating(driver.getRating());
				driverResponse.setCabId(driver.getCabId());

				int cabId = driver.getCabId();

				Cab cab = cabService.fetchCabDetails(cabId);

				driverResponse.setCab(cab);
				driverResponseList.add(driverResponse);

			}
			return driverResponseList;
		} else {
			throw new DriverNotFoundException("No Driver found with rating >= 4.5");
		}
	}

	@Override
	public List<DriverResponse> getAllDrivers() {
		List<Driver> driverList = driverRepository.findAll();
		List<DriverResponse> driverResponseList = new ArrayList<>();
		for (Driver driver : driverList) {
			DriverResponse driverResponse = new DriverResponse();
			driverResponse.setDriverId(driver.getUserId());
			driverResponse.setUsername(driver.getUsername());
			driverResponse.setAddress(driver.getAddress());
			driverResponse.setMobile(driver.getMobile());
			driverResponse.setEmail(driver.getEmail());
			driverResponse.setLicenseNo(driver.getLicenseNo());
			driverResponse.setRating(driver.getRating());
			driverResponse.setAvailable(driver.isAvailable());
			driverResponse.setCabId(driver.getCabId());

			int cabId = driver.getCabId();

			Cab cab = cabService.fetchCabDetails(cabId);

			driverResponse.setCab(cab);
			driverResponseList.add(driverResponse);

		}

		return driverResponseList;
	}

	@Override
	public List<DriverResponse> findByAvailabble()  {
		List<Driver> drivers = driverRepository.findByAvailable();
		if (!drivers.isEmpty()) {
			List<DriverResponse> driverResponseList = new ArrayList<>();
			for (Driver driver : drivers) {
				DriverResponse driverResponse = new DriverResponse();
				driverResponse.setDriverId(driver.getUserId());
				driverResponse.setUsername(driver.getUsername());
				driverResponse.setAddress(driver.getAddress());
				driverResponse.setMobile(driver.getMobile());
				driverResponse.setEmail(driver.getEmail());
				driverResponse.setLicenseNo(driver.getLicenseNo());
				driverResponse.setRating(driver.getRating());
				driverResponse.setAvailable(driver.isAvailable());
				driverResponse.setCabId(driver.getCabId());

				int cabId = driver.getCabId();

				Cab cab = cabService.fetchCabDetails(cabId);

				driverResponse.setCab(cab);
				driverResponseList.add(driverResponse);

			}
			return driverResponseList;
		} else {
			throw new DriverNotFoundException("No Drivers are available at this moment");
		}
	}

	@Override
	public List<TripResponse> getTripsByDriverId(int driverId) {
		return tripService.getTripsByDriverId(driverId);
	}

	@Override
	public TripResponse getTripById(int tripId) {
		// TODO Auto-generated method stub
		return tripService.getTripById(tripId);
	}

	@Override
	public float calculateBill(int customerId) {
		// TODO Auto-generated method stub
		return tripService.calculateBill(customerId);
	}

}
