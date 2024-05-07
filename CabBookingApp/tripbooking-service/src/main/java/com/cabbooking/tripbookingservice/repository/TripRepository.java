package com.cabbooking.tripbookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cabbooking.tripbookingservice.entity.TripBooking;


public interface TripRepository extends JpaRepository<TripBooking, Integer> {
	@Query("from TripBooking order by customerId")
    public List<TripBooking> findByCustomerid();

    @Query("from TripBooking order by Fromdate_time")
    public List<TripBooking> findByFromdate_time();

    @Query("from TripBooking where customerId=:customerId")
	public List<TripBooking> findByCustomerId(int customerId);

    @Query("from TripBooking where driverId=:driverId")
  	public List<TripBooking> findByDriverId(int driverId);


}
