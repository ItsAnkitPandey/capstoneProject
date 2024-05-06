package com.cabbooking.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.exception.AdminExceptions;
import com.cabbooking.adminservice.exception.DriverNotFoundException;
import com.cabbooking.adminservice.exception.NotFoundException;
import com.cabbooking.adminservice.model.Cab;
import com.cabbooking.adminservice.model.Customer;
import com.cabbooking.adminservice.model.Driver;
import com.cabbooking.adminservice.model.DriverResponse;
import com.cabbooking.adminservice.model.TripResponse;
import com.cabbooking.adminservice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CustomerServiceConsumer customerService;

	@Autowired
	private TripServiceConsumer tripService;

	@Autowired
	private CabServiceConsumer cabService;

	@Autowired
	private DriverServiceConsumer driverService;

	@Override
	public Admin createAdmin(Admin admin) throws AdminExceptions {
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminExceptions {
		Optional<Admin> opt = adminRepository.findById(admin.getAdminId());
		if (opt.isPresent()) {

			return adminRepository.save(admin);
		}
		throw new AdminExceptions("Admin not found with id: " + admin.getAdminId());

	}

	@Override
	public Admin deleteAdmin(int id) throws AdminExceptions {
		Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new AdminExceptions("Invalid Id"));
		adminRepository.delete(existingAdmin);

		return existingAdmin;
	}

	@Override
	public List<TripResponse> getAllTrips() throws AdminExceptions {
		List<TripResponse> trips = tripService.getAllTrips();
		return trips;
	}

	@Override
	public List<TripResponse> getTripsDriverwise(int driverId) {
		List<TripResponse> trips = tripService.getTripsByDriverId(driverId);

		if (trips.size() > 0) {
			return trips;
		} else {
			throw new AdminExceptions("No trips found");
		}

	}

	@Override
	public List<TripResponse> getTripsCustomerwise(int customerId) {
		List<TripResponse> trips = tripService.getTripsByCustomerId(customerId);
		if (trips.size() > 0) {
			return trips;
		} else {
			throw new AdminExceptions("No trips found");

		}
	}

	@Override
	public Cab insertCab(Cab cab) {
		return cabService.saveCab(cab);
	}

	@Override
	public Cab updateCab(Cab cab) throws NotFoundException {
		Optional<Cab> optCab = cabService.fetchCabDetails(cab.getCabId());
		if (optCab.isPresent()) {
			return cabService.editCab(cab);
		}
		throw new NotFoundException("Cab not found with id: " + cab.getCabId());
	}

	@Override
	public Cab getCabById(int cabId) throws NotFoundException {
		Optional<Cab> optionalCab = cabService.fetchCabDetails(cabId);
		if (optionalCab.isEmpty()) {
			throw new NotFoundException("Cab not found with id: " + cabId);
		}
		return optionalCab.get();
	}

	@Override
	public String deleteCab(int cabId) {
		Optional<Cab> optionalCab = cabService.fetchCabDetails(cabId);
		if (optionalCab.isEmpty()) {
			throw new NotFoundException("Cab not found with id: " + cabId);
		}
		cabService.deleteCab(cabId);
		return "Cab deleted successfully";
	}

	@Override
	public List<Cab> getAllCabs() {
		List<Cab> cabs = cabService.fetchAllCabs();
		return cabs;
	}

	@Override
	public String countCabsOfType() {
		String cabCount = cabService.countCabsOfType();
		return cabCount;
	}

	@Override
	public Driver insertDriver(Driver driver) {
		return driverService.insertDriver(driver);
	}

	@Override
	public DriverResponse viewDriverById(int driverId) throws DriverNotFoundException {
		DriverResponse driver = driverService.getDriverDetails(driverId);

		if (driver == null) {
			throw new DriverNotFoundException("No driver found with id:" + driverId);
		}
		return driver;
	}

	@Override
	public List<DriverResponse> getAllDrivers() {
		List<DriverResponse> drivers = driverService.fetchAllDriver();
		return drivers;
	}

	@Override
	public Driver updateDriver(Driver driver) throws DriverNotFoundException {
		DriverResponse optDriver = driverService.getDriverDetails(driver.getUserId());

		if (optDriver == null) {
			throw new DriverNotFoundException("No driver found with id:" + driver.getUserId());
		}
		return driverService.updateDriver(driver);
	}

	@Override
	public String deleteDriverById(int id) throws DriverNotFoundException {
		DriverResponse optionalDriver = driverService.getDriverDetails(id);
		if (optionalDriver == null) {
			throw new DriverNotFoundException("No driver found with id:" + id);
		}
		return "Driver deleted successfully";
	}

	@Override
	public List<DriverResponse> viewBestDriver() {
		List<DriverResponse> bestDrivers = driverService.viewBestDrivers();
		return bestDrivers;
	}

	@Override
	public List<DriverResponse> findByAvailabble()  {
		List<DriverResponse> availableDrivers = driverService.findByAvailable();
		return availableDrivers;
	}

	@Override
	public String deleteCustomer(int id) throws NotFoundException {
		Customer optCustomer = customerService.fetchCustomerDetails(id);
		if (optCustomer == null) {
			throw new NotFoundException("Customer not found with id: " + id);
		}
		customerService.deleteCustomer(id);
		return " Customer deleted successfully with id: "+ id;
	}

	@Override
	public List<Customer> allCustomer() {
		 List<Customer> customers = customerService.fetchAllCustomers();
		return customers;
	}

	@Override
	public Customer findCustomer(int customerId)  {
		Customer customer = customerService.fetchCustomerDetails(customerId);
		return customer;
	}

}
