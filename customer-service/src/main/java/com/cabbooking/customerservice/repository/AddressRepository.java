package com.cabbooking.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.customerservice.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {

}
