package com.oneInfo.challenge.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oneInfo.challenge.model.Path;

@Service
public class PathRepository {

	private final List<Path> paths = new ArrayList<>();
	
	public void save(Path paths) {
		System.out.println("agregando el camino: " + paths.getPath_id());
		this.paths.add(paths);
		System.out.println("Se agrego el camino correctamente");
	}
	
	public Optional<Path> findById(Long id) {
		System.out.println("Buscando el camino con el id: " + id);
		return this.paths.stream()
				.filter(path -> id.equals(path.getPath_id()))
				.findFirst();
		
	}

	public Optional<Path> findByStations(Long station_idA, Long station_idB) {
        return paths.stream()
                .filter(stationList -> stationList.getPath_id().equals(station_idA) 
                		&& stationList.getDestination_id().equals(station_idB) )
                .findFirst(); 
		
	}
	
    public List<Path> findPathsBySource(Long sourceId) {
        return paths.stream()
                    .filter(path -> path.getSource_id().equals(sourceId))
                    .collect(Collectors.toList());
    }
    
    public List<Path> getAllPaths() {
        return paths;
    }
}
