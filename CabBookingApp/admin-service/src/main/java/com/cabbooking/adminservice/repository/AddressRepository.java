package com.cabbooking.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.adminservice.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {

}
