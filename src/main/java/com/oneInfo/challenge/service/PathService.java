package com.oneInfo.challenge.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.oneInfo.challenge.model.Path;
import com.oneInfo.challenge.model.ShortestPathResult;

public interface PathService {
	
	public ResponseEntity<String> addPath(Path path);
	
	public ShortestPathResult findShortestPath(Long start, Long end);
	
	  public Optional<Path> findById(Long id);

}
