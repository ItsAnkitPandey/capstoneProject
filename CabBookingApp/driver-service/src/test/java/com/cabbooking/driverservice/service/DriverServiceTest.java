package com.cabbooking.driverservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.driverservice.entity.Driver;
import com.cabbooking.driverservice.exception.DriverNotFoundException;
import com.cabbooking.driverservice.model.Cab;
import com.cabbooking.driverservice.model.DriverResponse;
import com.cabbooking.driverservice.repository.AddressRepository;
import com.cabbooking.driverservice.repository.DriverRepository;

@SpringBootTest
public class DriverServiceTest {
	@Mock
	private DriverRepository driverRepository;

	@Mock
	private AddressRepository addressRepository;

	@Mock
	private CabServiceConsumer cabService;

	@InjectMocks
	private DriverServiceImpl driverService;

	@Test
	public void testInsertDriver() {
		Driver driver = new Driver();
		when(driverRepository.save(driver)).thenReturn(driver);

		Driver insertedDriver = driverService.insertDriver(driver);

		assertEquals(driver, insertedDriver);
	}

	@Test
	public void testViewDriverById() throws DriverNotFoundException {
		int driverId = 1;
		Driver driver = new Driver();
		driver.setUserId(driverId);
		Optional<Driver> optionalDriver = Optional.of(driver);
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);

		int cabId = driver.getCabId();
		Cab mockCab = new Cab();
		when(cabService.fetchCabDetails(cabId)).thenReturn(mockCab);

		DriverResponse driverResponse = driverService.viewDriverById(driverId);

		assertEquals(driver.getUserId(), driverResponse.getDriverId());
	}

	@Test
	public void testViewDriverByIdWithNotFoundException() {
		int driverId = 1;
		Optional<Driver> optionalDriver = Optional.empty();
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);

		assertThrows(DriverNotFoundException.class, () -> driverService.viewDriverById(driverId));
	}

	@Test
	public void testDeleteDriverById() throws DriverNotFoundException {
		int driverId = 1;
		Driver driver = new Driver();
		driver.setUserId(driverId);
		driver.setUsername("ankit");
		driver.setPassword("123456");
		driver.setRating(4.5f);
		driver.setMobile("6391457895");
		driver.setLicenseNo("nu76gy");
		driver.setEmail("any@mail.com");

		Optional<Driver> optionalDriver = Optional.of(driver);
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);
		doNothing().when(driverRepository).delete(driver);

		String result = driverService.deleteDriverById(driverId);

		assertEquals("Driver Id " + driverId + " deleted successfully ", result);

	}

	@Test
	public void testDeleteDriverByIdWithNotFoundException() {
		int driverId = 1;
		Optional<Driver> optionalDriver = Optional.empty();
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);

		assertThrows(DriverNotFoundException.class, () -> driverService.viewDriverById(driverId));
	}

	@Test
	public void testGetAllDrivers() {
		Driver driver = new Driver();
		driver.setUserId(111);
		driver.setUsername("ankit");
		driver.setPassword("123456");
		driver.setRating(4.5f);
		driver.setMobile("6391457895");
		driver.setLicenseNo("nu76gy");
		driver.setEmail("any@mail.com");

		Driver driver1 = new Driver();
		driver1.setUserId(121);
		driver1.setUsername("akit");
		driver1.setPassword("123456");
		driver1.setRating(4.5f);
		driver1.setMobile("6291457895");
		driver1.setLicenseNo("nm76gy");
		driver1.setEmail("any1@mail.com");

		List<Driver> drivers = new ArrayList<>();
		drivers.add(driver);
		drivers.add(driver1);

		when(driverRepository.findAll()).thenReturn(drivers);

		List<DriverResponse> driverResponses = driverService.getAllDrivers();

		assertEquals(2, driverResponses.size());
	}

	@Test
	public void testUpdateDriver() throws DriverNotFoundException {
		int driverId = 1;
		Driver driver = new Driver();
		driver.setUserId(driverId);
		Optional<Driver> optionalDriver = Optional.of(driver);
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);
		when(driverRepository.save(driver)).thenReturn(driver);

		Driver updatedDriver = driverService.updateDriver(driver);

		assertEquals(driver, updatedDriver);
	}

	@Test
	public void testUpdateDriverWithNotFoundException() {
		int driverId = 1;
		Driver driver = new Driver();
		driver.setUserId(driverId);
		Optional<Driver> optionalDriver = Optional.empty();
		when(driverRepository.findById(driverId)).thenReturn(optionalDriver);

		assertThrows(DriverNotFoundException.class, () -> driverService.updateDriver(driver));
	}

	@Test
	public void testFindByAvailable() throws DriverNotFoundException {
		List<Driver> drivers = new ArrayList<>();
		Driver driver = new Driver();
		driver.setUserId(111);
		driver.setUsername("ankit");
		driver.setPassword("123456");
		driver.setRating(4.5f);
		driver.setMobile("6391457895");
		driver.setLicenseNo("nu76gy");
		driver.setEmail("any@mail.com");
		driver.setAvailable(true);
		driver.setCabId(1);

		Driver driver1 = new Driver();
		driver1.setUserId(121);
		driver1.setUsername("akit");
		driver1.setPassword("123456");
		driver1.setRating(4.5f);
		driver1.setMobile("6291457895");
		driver1.setLicenseNo("nm76gy");
		driver1.setEmail("any1@mail.com");
		driver1.setAvailable(true);
		driver1.setCabId(2);

		drivers.add(driver1);
		drivers.add(driver);
		when(driverRepository.findByAvailable()).thenReturn(drivers);

		List<DriverResponse> driverResponses = driverService.findByAvailabble();

		// Assertions
		assertFalse(driverResponses.isEmpty());
		assertEquals(2, driverResponses.size());
	}

	@Test
	public void testViewBestDriver() throws DriverNotFoundException {
		// Mocking drivers with rating >= 4.5
		List<Driver> drivers = new ArrayList<>();
		Driver driver = new Driver();
		driver.setUserId(111);
		driver.setUsername("ankit");
		driver.setPassword("123456");
		driver.setRating(4.6f);
		driver.setMobile("6391457895");
		driver.setLicenseNo("nu76gy");
		driver.setEmail("any@mail.com");
		driver.setAvailable(true);
		driver.setCabId(1);

		Driver driver1 = new Driver();
		driver1.setUserId(121);
		driver1.setUsername("akit");
		driver1.setPassword("123456");
		driver1.setRating(3.5f);
		driver1.setMobile("6291457895");
		driver1.setLicenseNo("nm76gy");
		driver1.setEmail("any1@mail.com");
		driver1.setAvailable(true);
		driver1.setCabId(2);

		drivers.add(driver1);
		when(driverRepository.viewBestDriver()).thenReturn(drivers);

		List<DriverResponse> driverResponses = driverService.viewBestDriver();

		assertFalse(driverResponses.isEmpty());
		assertEquals(1, driverResponses.size());
	}

	@Test
	public void testViewBestDriver_NoDriversFound() {
		when(driverRepository.viewBestDriver()).thenReturn(new ArrayList<>());

		assertThrows(DriverNotFoundException.class, () -> driverService.viewBestDriver());
	}

}
