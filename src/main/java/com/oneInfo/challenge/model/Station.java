package com.oneInfo.challenge.model;

public class Station {
	
	private Long station_id;
	private String name;
	
	public Long getStation_id() {
		return station_id;
	}
	public void setStation_id(Long station_id) {
		this.station_id = station_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Station(Long station_id, String name) {
		super();
		this.station_id = station_id;
		this.name = name;
	}

}
