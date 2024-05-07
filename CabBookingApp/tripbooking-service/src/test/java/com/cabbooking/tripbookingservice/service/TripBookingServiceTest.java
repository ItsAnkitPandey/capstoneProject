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
import com.cabbooking.tripbookingservice.model.TripResponse;
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
        Customer customer = new Customer();
        customer.setUserId(1);
        customer.setJourney_status(false);

        DriverResponse driver = new DriverResponse();
        driver.setDriverId(1);
        driver.setAvailable(true);
        Cab cab = new Cab();
        cab.setRatePerKm(10.0f);
        driver.setCab(cab);

        List<DriverResponse> availableDrivers = new ArrayList<>();
        availableDrivers.add(driver);

        TripBooking tripBooking = new TripBooking();
        tripBooking.setCustomerId(1);
        tripBooking.setKm(5.0f);

        when(customerService.fetchCustomerDetails(1)).thenReturn(customer);
        when(driverService.findByAvailable()).thenReturn(availableDrivers);
        when(driverService.getDriverDetails(1)).thenReturn(driver);

        when(tripRepository.save(any(TripBooking.class))).thenReturn(tripBooking);

        TripBooking addedTrip = tripBookingService.AddTrip(tripBooking);

        verify(customerService).fetchCustomerDetails(1);
        verify(driverService).findByAvailable();
        verify(tripRepository).save(tripBooking);

        assertEquals(50.0f, addedTrip.getTotalamount());
        assertEquals(1, addedTrip.getDriverId());
    }
	
	@Test
    public void testAllTrip() {
        TripBooking tripBooking1 = new TripBooking();
        tripBooking1.setTripBookingId(101);
        tripBooking1.setKm(50);
        tripBooking1.setFrom_location("hata");
        tripBooking1.setTo_location("gkp");
        tripBooking1.setPayment(true);
        
        Customer customer = new Customer();
        customer.setUserId(1);
        customer.setJourney_status(false);
        tripBooking1.setCustomerId(1);
        
        DriverResponse driver = new DriverResponse();
        driver.setDriverId(1);
        driver.setAvailable(true);
        
        Cab cab = new Cab();
        cab.setRatePerKm(10.0f);
        driver.setCab(cab);

        TripBooking tripBooking2 = new TripBooking();
        tripBooking2.setTripBookingId(701);
        tripBooking2.setKm(50);
        tripBooking2.setFrom_location("data");
        tripBooking2.setTo_location("fara");
        tripBooking2.setPayment(true);
        
        Customer customer1 = new Customer();
        customer1.setUserId(2);
        customer1.setJourney_status(false);
        tripBooking2.setCustomerId(2);
        
        DriverResponse driver1 = new DriverResponse();
        driver1.setDriverId(2);
        driver1.setAvailable(true);
        
        Cab cab1 = new Cab();
        cab.setRatePerKm(10.0f);
        driver1.setCab(cab1);

        List<TripBooking> trips = new ArrayList<>();
        trips.add(tripBooking1);
        trips.add(tripBooking2);

        when(tripRepository.findAll()).thenReturn(trips);

        when(customerService.fetchCustomerDetails(1)).thenReturn(customer);

        when(driverService.getDriverDetails(1)).thenReturn(driver);

        List<TripResponse> tripResponses = tripBookingService.alltrip();

        assertEquals(2, tripResponses.size());
    }
	
}
