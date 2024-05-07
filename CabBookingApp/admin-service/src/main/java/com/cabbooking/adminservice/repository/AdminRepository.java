package com.cabbooking.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbooking.adminservice.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
