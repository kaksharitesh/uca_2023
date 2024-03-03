package com.uca.ds.graphs;

public class DirectedWeightedEdge {
    private final int from;
    private final int to;

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    private final double weight;

    public int getFrom() {
        return from;
    }

    public DirectedWeightedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
