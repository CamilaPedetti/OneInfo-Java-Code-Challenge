package com.oneInfo.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneInfo.challenge.model.Station;
import com.oneInfo.challenge.repository.StationRepository;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	StationRepository stationRepository;

	@Override
	public void addStation(Station station) {
		stationRepository.findById(station.getStation_id())
		.ifPresentOrElse(
				stationFind -> stationRepository.replace(station), 
				() -> stationRepository.save(station));
		
	}

}
