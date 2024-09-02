package com.oneInfo.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.model.ShortestPathResult;
import com.oneInfo.challenge.service.PathService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", methods= {RequestMethod.PUT})
public class PathController {
	
	@Autowired
	PathService pathService;

	@ResponseStatus
	@PutMapping("/paths/{id}")
	ResponseEntity<String> addPaths(@RequestBody Path path, @PathVariable Long id) {
		path.setPath_id(id);
		return pathService.addPath(path);
	}

	@ResponseStatus
	@GetMapping("/GET /paths/{source_id}/{destination_id}")
	ResponseEntity<ShortestPathResult> findShortestPath(@PathVariable Long source_id, @PathVariable Long destination_id) {
		ShortestPathResult result = pathService.findShortestPath(source_id, destination_id);
        return ResponseEntity.ok(result);
	}
}
