package com.cabbooking.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.customerservice.entity.Address;
import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.exception.IdInvalid;
import com.cabbooking.customerservice.repository.AddressRepository;
import com.cabbooking.customerservice.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private AddressRepository addressRepository;

	@Test
	void saveCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		when(customerRepository.save(customer)).thenReturn(customer);
		Customer actualCustomer = customerService.saveCustomer(customer);

		assertEquals("ankit", actualCustomer.getUsername());
	}

	@Test
	void findCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		when(customerRepository.findById(111)).thenReturn(Optional.of(customer));

		Customer foundCustomer = customerService.findCustomer(111);

		assertEquals(customer, foundCustomer);
	}

	@Test
	void updateCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		when(customerRepository.findById(customer.getUserId())).thenReturn(Optional.of(customer));

		Customer updatedCustomer = new Customer();
		updatedCustomer.setUserId(111);
		updatedCustomer.setUsername("Ankit Pandey");
		updatedCustomer.setPassword("1234565");
		updatedCustomer.setEmail("ap@ma.com");
		updatedCustomer.setMobile("6391735294");
		updatedCustomer.setJourney_status(false);

		Address updatedAddress = new Address();
		updatedAddress.setAddrId(111);
		updatedAddress.setCity("add mall");
		updatedAddress.setState("UP");
		updatedAddress.setPincode("203145");
		updatedCustomer.setAddress(updatedAddress);

		customerService.updateCustomer(updatedCustomer);

		verify(customerRepository).save(updatedCustomer);
	}

	@Test
	void deleteCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		when(customerRepository.findById(111)).thenReturn(Optional.of(customer));

		doNothing().when(customerRepository).delete(customer);

		customerService.deleteCustomer(111);

		verify(customerRepository, times(1)).findById(111);
		verify(customerRepository, times(1)).delete(customer);
	}

	@Test
	void allCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		Customer customer1 = new Customer();
		customer1.setUserId(115);
		customer1.setUsername("amiit");
		customer1.setPassword("1234565");
		customer1.setEmail("amm@ma.com");
		customer1.setMobile("6391765494");
		customer1.setJourney_status(false);

		Address address1 = new Address();
		address1.setAddrId(115);
		address1.setCity("maldives");
		address1.setState("UP");
		address1.setPincode("205645");
		customer1.setAddress(address1);

		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer);

		when(customerRepository.findAll()).thenReturn(customers);

		List<Customer> customerList = customerService.allCustomer();
		assertEquals(2, customerList.size());

	}

	@Test
	void validCustomerTest() {
		Customer customer = new Customer();
		customer.setUserId(111);
		customer.setUsername("ankit");
		customer.setPassword("1234565");
		customer.setEmail("a@ma.com");
		customer.setMobile("6391735294");
		customer.setJourney_status(false);

		Address address = new Address();
		address.setAddrId(111);
		address.setCity("add mall");
		address.setState("UP");
		address.setPincode("203145");
		customer.setAddress(address);

		Customer customer1 = new Customer();
		customer1.setUserId(115);
		customer1.setUsername("amiit");
		customer1.setPassword("1234565");
		customer1.setEmail("amm@ma.com");
		customer1.setMobile("6391765494");
		customer1.setJourney_status(false);

		Address address1 = new Address();
		address1.setAddrId(115);
		address1.setCity("maldives");
		address1.setState("UP");
		address1.setPincode("205645");
		customer1.setAddress(address1);

		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer);

		when(customerRepository.findAll()).thenReturn(customers);

		Customer validCustomer = customerService.vaildCustomer("a@ma.com", "1234565");

		assertEquals("ankit", validCustomer.getUsername());
		 assertThrows(IdInvalid.class, () -> customerService.vaildCustomer("ankit@invalid.com", "11114544"));
    }

	}


