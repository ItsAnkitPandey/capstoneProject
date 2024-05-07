package com.cabbooking.cabservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.cabservice.entity.Cab;
import com.cabbooking.cabservice.exception.NotFoundException;
import com.cabbooking.cabservice.repository.CabRepository;

@SpringBootTest
public class CabServiceTest {

    @Mock
    private CabRepository cabRepository;


    @InjectMocks
    private CabServiceImpl cabService;

  
    @Test
    public void testInsertCab() {
    	Cab cab = new Cab();
		cab.setCabId(111);
		cab.setCarType("xuv");
		cab.setRatePerKm(100);
        when(cabRepository.save(cab)).thenReturn(cab);
        Cab insertedCab = cabService.insertCab(cab);
        assertNotNull(insertedCab);
        assertEquals(cab, insertedCab);
    }

    @Test
    public void testUpdateCab()  {
        Cab cab = new Cab();
        cab.setCabId(1);
        Optional<Cab> optionalCab = Optional.of(cab);
        when(cabRepository.findById(1)).thenReturn(optionalCab);
        when(cabRepository.save(cab)).thenReturn(cab);

        Cab updatedCab = cabService.updateCab(cab);

        assertEquals(cab, updatedCab);
    }
   
    @Test
    public void testUpdateCabWithNotFoundException() {
        Cab cab = new Cab();
        cab.setCabId(1);
        Optional<Cab> optionalCab = Optional.empty();
        when(cabRepository.findById(1)).thenReturn(optionalCab);

        assertThrows(NotFoundException.class, () -> cabService.updateCab(cab));
    }

    @Test
    public void testGetAllCabs() {
    	Cab cab = new Cab();
		cab.setCabId(111);
		cab.setCarType("xuv");
		cab.setRatePerKm(100);
		
		Cab cab1 = new Cab();
		cab1.setCabId(121);
		cab1.setCarType("xrv");
		cab1.setRatePerKm(400);
		
		Cab cab2 = new Cab();
		cab2.setCabId(115);
		cab2.setCarType("ase");
		cab2.setRatePerKm(120);
		

		List<Cab> cabs = new ArrayList<>();
		cabs.add(cab);
		cabs.add(cab1);
		cabs.add(cab2);
				
		when(cabRepository.findAll()).thenReturn(cabs);
		
		List<Cab> cabList = cabService.getAllCabs();		
		assertEquals(3,cabList.size());
    }

    @Test
	void testDeleteCab() {		
    	Cab cab = new Cab();
		cab.setCabId(111);
		cab.setCarType("xuv");
		cab.setRatePerKm(100);
		
		when(cabRepository.findById(111)).thenReturn(Optional.of(cab));
		
		doNothing().when(cabRepository).delete(cab);
		
		cabService.deleteCab(111);
		
		verify(cabRepository,times(1)).findById(111);
		verify(cabRepository,times(1)).delete(cab);		
	}
    
    @Test
    public void testCountCabsOfType() {
        List<Cab> totalCabs = new ArrayList<>();
        Cab cab = new Cab();
		cab.setCabId(111);
		cab.setCarType("xuv");
		cab.setRatePerKm(100);
		
		Cab cab1 = new Cab();
		cab1.setCabId(121);
		cab1.setCarType("kiv");
		cab1.setRatePerKm(100);
		
		totalCabs.add(cab1);
		totalCabs.add(cab);
        when(cabRepository.findAll()).thenReturn(totalCabs);

        int count = cabService.countCabsOfType();

        assertEquals(totalCabs.size(), count);
    }
    
    @Test
    public void testGetCabById() throws NotFoundException {
        int cabId = 1;
        Cab cab = new Cab();
        cab.setCabId(cabId);
        Optional<Cab> optionalCab = Optional.of(cab);
        when(cabRepository.findById(cabId)).thenReturn(optionalCab);

        Cab returnedCab = cabService.getCabById(cabId);

        assertEquals(cab, returnedCab);
    }
    
    @Test
    public void testGetCabById_NotFound() {
        int cabId = 1;
        Optional<Cab> optionalCab = Optional.empty();
        when(cabRepository.findById(cabId)).thenReturn(optionalCab);

        assertThrows(NotFoundException.class, () -> cabService.getCabById(cabId));
    }
}
