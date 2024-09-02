package com.oneInfo.challenge.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oneInfo.challenge.model.Station;

@Service
public class StationRepository {

	private final List<Station> stations = new ArrayList<>();

	public Optional<Station> findById(Long id) {
		System.out.println("Buscando la estación con el id: " + id);
		return this.stations.stream()
				.filter(station -> id.equals(station.getStation_id()))
				.findFirst();
	}
	
	public void save(Station station) {
		System.out.println("agregando la estación: " + station.getStation_id());
		this.stations.add(station);
		System.out.println("Se agrego la estación correctamente");
	}

	public void replace(Station station) {
		System.out.println("Reemplazando la estación: " + station.getName() + "con el nombre " + station.getName());
		Optional<Station> replaceStation = findById(station.getStation_id());
		replaceStation.ifPresent(s -> s.setName(station.getName()));
		System.out.println("Se reemplazo la estación correctamente");
	}

}
