package com.cabbooking.cabservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cabbooking.cabservice.entity.Cab;


public interface CabRepository extends JpaRepository<Cab, Integer> {
	@Query("select distinct carType from Cab")
	public List<String> viewCarType(String carType);

}
