package com.cabbooking.driverservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.driverservice.entity.Address;



public interface AddressRepository extends JpaRepository<Address, Integer> {

}
