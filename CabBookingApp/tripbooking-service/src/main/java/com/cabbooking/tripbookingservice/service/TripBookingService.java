package com.cabbooking.tripbookingservice.service;

import java.util.List;

import com.cabbooking.tripbookingservice.entity.TripBooking;
import com.cabbooking.tripbookingservice.exception.NotFoundException;
import com.cabbooking.tripbookingservice.model.TripResponse;

public interface TripBookingService {

	public TripBooking AddTrip(TripBooking tripBooking);

	public List<TripResponse> alltrip();

	public TripResponse getTripById(int tripId);

	public TripBooking updateTrip(TripBooking tripBooking) throws NotFoundException;

	public String deleteTrip(int id) throws NotFoundException;

	public String tripEnd(int id) throws NotFoundException;

	float calculateBill(int customerId);

	List<TripResponse> getTripHistoryByDriver(int driverId);

	List<TripResponse> getTripHistoryByCustomer(int customerId);

}
