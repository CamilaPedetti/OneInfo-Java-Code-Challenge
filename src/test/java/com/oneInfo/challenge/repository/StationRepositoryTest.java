package com.oneInfo.challenge.repository;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oneInfo.challenge.model.Station;

@SpringBootTest
public class StationRepositoryTest {
	
	@Autowired
	StationRepository stationRepository;
	
	@BeforeEach
	void setUp() {
		Station stationm = new Station(1L,"Buenos Aires");
		Station station2 = new Station(2L,"Rosario");
		Station station3 = new Station(3L,"Cordoba");
		stationRepository.save(stationm);
		stationRepository.save(station2);
		stationRepository.save(station3);
		
	}
	
	@Test
	public void findByIdCaseFound() {
		Optional<Station> stationFind = stationRepository.findById(2L);
		assertEquals(stationFind.get().getName(),"Rosario");
	}
	
	@Test
	public void findByIdCaseNotFound() {
		Optional<Station> stationFind = stationRepository.findById(4L);
		assertEquals(stationFind, Optional.empty());
	}
	
	
	@Test
	public void replaceFound() {
		Station stationReeplace = new Station(1L, "Corrientes");
		stationRepository.replace(stationReeplace);
		assertEquals(stationRepository.findById(1L).get().getName(), "Corrientes"); 
	}
	
	@Test
	public void replaceNotFound() {
		Station stationReeplace = new Station(5L, "Corrientes");
		stationRepository.replace(stationReeplace);
		assertEquals(stationRepository.findById(5L), Optional.empty()); 
	}


}
