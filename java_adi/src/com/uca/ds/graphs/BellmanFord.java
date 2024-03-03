package com.uca.ds.graphs;

import java.util.Arrays;

public class BellmanFord {
    private final double[] distTo;
    private final int[] edgeTo;

    public BellmanFord(DirectedWeightedGraph g) {
        distTo = new double[g.V];
        edgeTo = new int[g.V];
        distTo[0] = 0.0;
        for (int i = 1; i < g.V; i++) {
            distTo[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < g.V; i++) {
            for (int j = 0; j < g.V; j++) {
                for (DirectedWeightedEdge e : g.adj(j)) {
                    relax(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        DirectedWeightedGraph g = new DirectedWeightedGraph(8);
        g.addEdge(new DirectedWeightedEdge(0, 1, 5.0));
        g.addEdge(new DirectedWeightedEdge(0, 4, 9.0));
        g.addEdge(new DirectedWeightedEdge(0, 7, 8.0));
        g.addEdge(new DirectedWeightedEdge(1, 2, 12.0));
        g.addEdge(new DirectedWeightedEdge(1, 3, 15.0));
        g.addEdge(new DirectedWeightedEdge(1, 7, 4.0));
        g.addEdge(new DirectedWeightedEdge(2, 3, 3.0));
        g.addEdge(new DirectedWeightedEdge(2, 6, 11.0));
        g.addEdge(new DirectedWeightedEdge(3, 6, 9.0));
        g.addEdge(new DirectedWeightedEdge(4, 5, 4.0));
        g.addEdge(new DirectedWeightedEdge(4, 6, 20.0));
        g.addEdge(new DirectedWeightedEdge(4, 7, 5.0));
        g.addEdge(new DirectedWeightedEdge(5, 2, 1.0));
        g.addEdge(new DirectedWeightedEdge(5, 6, 13.0));
        g.addEdge(new DirectedWeightedEdge(7, 5, 6.0));
        g.addEdge(new DirectedWeightedEdge(7, 2, 7.0));
        BellmanFord k = new BellmanFord(g);
        System.out.println(Arrays.toString(k.distTo));
        System.out.println(Arrays.toString(k.edgeTo));
    }

    private void relax(DirectedWeightedEdge e) {
        int v = e.getFrom();
        int w = e.getTo();
        if (distTo[w] > distTo[v] + e.getWeight()) {
            edgeTo[w] = v;
            distTo[w] = distTo[v] + e.getWeight();
        }
    }
}
