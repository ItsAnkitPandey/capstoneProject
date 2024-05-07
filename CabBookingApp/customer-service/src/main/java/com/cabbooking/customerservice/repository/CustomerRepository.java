package com.cabbooking.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.customerservice.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
