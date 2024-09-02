package com.oneInfo.challenge.model;

public class PathNode {
    private final Long nodeId;
    private final Double cost;

    public PathNode(Long nodeId, Double cost) {
        this.nodeId = nodeId;
        this.cost = cost;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public Double getCost() {
        return cost;
    }
}