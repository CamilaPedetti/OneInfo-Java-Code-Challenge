package com.oneInfo.challenge.model;

import java.util.List;


public class ShortestPathResult {
    private final List<Long> path;
    private final Double totalCost;

    public ShortestPathResult(List<Long> path, Double totalCost) {
        this.path = path;
        this.totalCost = totalCost;
    }

    public List<Long> getPath() {
        return path;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}