package com.cabbooking.tripbookingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.tripbookingservice.entity.TripBooking;
import com.cabbooking.tripbookingservice.model.Cab;
import com.cabbooking.tripbookingservice.model.Customer;
import com.cabbooking.tripbookingservice.model.DriverResponse;
import com.cabbooking.tripbookingservice.repository.TripRepository;

@SpringBootTest
public class TripBookingServiceTest {
	@InjectMocks
	private TripBookingServiceImpl tripBookingService;

	@Mock
	private TripRepository tripRepository;

	@Mock
	private CustomerServiceConsumer customerService;

	@Mock
	private DriverServiceConsumer driverService;
	
	@Test
    void addTripTest() {
        // Mock customer
        Customer customer = new Customer();
        customer.setUserId(1);
        customer.setJourney_status(false);

        // Mock driver
        DriverResponse driver = new DriverResponse();
        driver.setDriverId(1);
        driver.setAvailable(true);
        Cab cab = new Cab();
        cab.setRatePerKm(10.0f);
        driver.setCab(cab);

        // Mock available drivers list
        List<DriverResponse> availableDrivers = new ArrayList<>();
        availableDrivers.add(driver);

        // Mock trip booking
        TripBooking tripBooking = new TripBooking();
        tripBooking.setCustomerId(1);
        tripBooking.setKm(5.0f);

        // Mock behavior of customerService and driverService
        when(customerService.fetchCustomerDetails(1)).thenReturn(customer);
        when(driverService.findByAvailable()).thenReturn(availableDrivers);
        when(driverService.getDriverDetails(1)).thenReturn(driver);

        // Mock behavior of tripRepository
        when(tripRepository.save(any(TripBooking.class))).thenReturn(tripBooking);

        // Call the method under test
        TripBooking addedTrip = tripBookingService.AddTrip(tripBooking);

        // Verify interactions
        verify(customerService).fetchCustomerDetails(1);
        verify(driverService).findByAvailable();
        verify(driverService).getDriverDetails(1);
        verify(tripRepository).save(tripBooking);

        // Assertions
        assertEquals(50.0f, addedTrip.getTotalamount());
        assertEquals(1, addedTrip.getDriverId());
    }
}
