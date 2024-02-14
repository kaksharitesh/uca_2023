package com.uca.ds.graphs;

public class WeightedEdge implements Comparable<WeightedEdge> {
    public int to;
    public int from;

    public double weight;

    public WeightedEdge(int to, int from, double weight) {
        this.weight = weight;
        this.to = to;
        this.from = from;
    }

    public int either() {
        return to;
    }

    public int other(int v) {
        if (to == v) return from;
        if (from == v) throw new RuntimeException("not a valid vertex");
        return to;
    }


    @Override
    public int compareTo(WeightedEdge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "Edge:"+ to +
                "->" + from +
                "(" + weight +
                ')';
    }
}
