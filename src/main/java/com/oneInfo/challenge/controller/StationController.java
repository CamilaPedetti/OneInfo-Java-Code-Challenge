package com.oneInfo.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oneInfo.challenge.model.Station;
import com.oneInfo.challenge.service.StationService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", methods= {RequestMethod.PUT})
public class StationController {
	
	@Autowired
	StationService stationService;
	
	@ResponseStatus(code = HttpStatus.OK) 
	@PutMapping("/stations/{id}")
	ResponseEntity addStation(@RequestBody String name, @PathVariable Long id) {
		Station station = new Station(id,name);
		stationService.addStation(station);
		return ResponseEntity.ok().build();

	}
}
