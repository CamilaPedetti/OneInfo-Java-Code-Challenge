package com.oneInfo.challenge.service;

import org.springframework.http.ResponseEntity;

import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.model.ShortestPathResult;

public interface PathService {
	
	public ResponseEntity<String> addPath(Path path);
	
	public ShortestPathResult findShortestPath(Long start, Long end);

}
