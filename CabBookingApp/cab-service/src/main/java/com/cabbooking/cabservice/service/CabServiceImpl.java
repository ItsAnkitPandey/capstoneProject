package com.cabbooking.cabservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.cabservice.entity.Cab;
import com.cabbooking.cabservice.exception.NotFoundException;
import com.cabbooking.cabservice.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService {
	@Autowired
	private CabRepository cabRepository;

	@Override
	public Cab insertCab(Cab cab) {
		return cabRepository.save(cab);
	}

	@Override
	public Cab updateCab(Cab cab) throws NotFoundException {
		Optional<Cab> optionalCab = cabRepository.findById(cab.getCabId());
		if (optionalCab.isEmpty()) {
			throw new NotFoundException("Cab not existing with id: " + cab.getCabId());
		}
		cabRepository.save(cab);
		return cab;
	}

	@Override
	public List<String> viewCabsOfType(String carType) throws NotFoundException {
		List<String> cabs = cabRepository.viewCarType(carType);
		return cabs;
	}

	@Override
	public int countCabsOfType() throws NotFoundException {
		List<Cab> totalCabs = cabRepository.findAll();
		return totalCabs.size();
	}

	@Override
	public void deleteCab(int cabId) {
		Optional<Cab> optionalCab = cabRepository.findById(cabId);
		if (optionalCab.isEmpty()) {
			throw new NotFoundException("Cab not existing with id: " + cabId);

		}
		Cab cab = optionalCab.get();
		cabRepository.delete(cab);
	}

	@Override
	public List<Cab> getAllCabs() {
		List<Cab> cabList = cabRepository.findAll();

		return cabList;
	}

	@Override
	public Cab getCabById(int cabId) throws NotFoundException {
		Optional<Cab> optionalCab = cabRepository.findById(cabId);
		if (optionalCab.isEmpty()) {
			throw new NotFoundException("No Cab found for id: " + cabId);
		}
		Cab cab = optionalCab.get();
		return cab;
	}

}
