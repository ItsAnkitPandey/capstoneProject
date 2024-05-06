package com.cabbooking.cabservice.service;

import java.util.List;

import com.cabbooking.cabservice.entity.Cab;
import com.cabbooking.cabservice.exception.NotFoundException;

public interface CabService {
	public Cab insertCab(Cab cab);

	public Cab updateCab(Cab cab) throws NotFoundException;

	public Cab getCabById(int cabId) throws NotFoundException;

	public void deleteCab(int cabId);

	List<Cab> getAllCabs();

	public int countCabsOfType() throws NotFoundException;

	public List<String> viewCabsOfType(String carType) throws NotFoundException;

}
