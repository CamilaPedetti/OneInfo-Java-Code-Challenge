package com.oneInfo.challenge.model;

public class Path {
	
	private Long path_id;
	private Long source_id;
	private Long destination_id;
	private Double cost;
	
	public Path(long path_id, Long sourceId, long destination_id, double cost) {
		this.path_id = path_id;
		this.source_id = sourceId;
		this.destination_id = destination_id;
		this.cost = cost;
	}
	
	public Long getPath_id() {
		return path_id;
	}
	public void setPath_id(Long path_id) {
		this.path_id = path_id;
	}
	public Long getSource_id() {
		return source_id;
	}
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}
	public Long getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(Long destination_id) {
		this.destination_id = destination_id;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}

}
