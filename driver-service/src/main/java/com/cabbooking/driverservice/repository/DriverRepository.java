package com.cabbooking.driverservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cabbooking.driverservice.entity.Driver;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

	@Query("from Driver d where d.rating >= 4.5 ")
	public List<Driver> viewBestDriver();

	@Query("from Driver d where d.available = true")
	public List<Driver> findByAvailable();
}
