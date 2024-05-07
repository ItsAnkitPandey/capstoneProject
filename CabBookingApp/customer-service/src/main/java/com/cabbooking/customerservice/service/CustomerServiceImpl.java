package com.cabbooking.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.exception.IdInvalid;
import com.cabbooking.customerservice.model.TripBooking;
import com.cabbooking.customerservice.model.TripResponse;
import com.cabbooking.customerservice.repository.AddressRepository;
import com.cabbooking.customerservice.repository.CustomerRepository;

import jakarta.validation.Valid;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private TripServiceConsumer tripService;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findCustomer(int customerId) throws IdInvalid {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new IdInvalid("Customer not existing with id: " + customerId);
		}
		Customer customer = optionalCustomer.get();
		return customer;

	}

	@Override
	public Customer updateCustomer(Customer customer) throws IdInvalid {
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getUserId());
		if (optionalCustomer.isEmpty()) {
			throw new IdInvalid("No Customer found ");
		}
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public String deleteCustomer(int id) throws IdInvalid {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IdInvalid("Customer with ID " + id + " does not exit.."));
		addressRepository.delete(customer.getAddress());
		customerRepository.delete(customer);

		return "deleted successfully...";
	}

	@Override
	public List<Customer> allCustomer()  {
		List<Customer> customer = customerRepository.findAll();
		return customer;
	}

	@Override
	public Customer vaildCustomer(String Email, String Password) throws IdInvalid {
		List<Customer> customer = customerRepository.findAll();
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getEmail().equals(Email) && customer.get(i).getPassword().equals(Password))
				return customer.get(i);
		}
		throw new IdInvalid("Invalid Email and password");
	}

	@Override
	public TripBooking Book(@Valid @RequestBody TripBooking trip) {
		return tripService.Book(trip);
	}

	@Override
	public String cancel(int customerId) {
		return tripService.cancel(customerId);
	}

	@Override
	public List<TripResponse> getTripsByCustomerId(int customerId) {
		return tripService.getTripsByCustomerId(customerId);
	}

	@Override
	public float calculateBill(int customerId) {
		return tripService.calculateBill(customerId);
	}

	@Override
	public TripResponse getTripById(int tripId) {
		return tripService.getTripById(tripId);
	}

}
