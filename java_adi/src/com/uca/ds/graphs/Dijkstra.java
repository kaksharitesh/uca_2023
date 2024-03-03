package com.uca.ds.graphs;

import com.uca.ds.queues.IndexedPriorityQueue;

import java.util.Arrays;

public class Dijkstra {
    private final double[] distTo;
    private final int[] edgeTo;

    private final IndexedPriorityQueue q;

    public Dijkstra(DirectedWeightedGraph g) {
        distTo = new double[g.V];
        edgeTo = new int[g.V];
        q = new IndexedPriorityQueue(g.V);
        distTo[0] = 0.0;
        q.insert(0, 0.0);
        for (int i = 1; i < g.V; i++) {
            distTo[i] = Double.MAX_VALUE;
            q.insert(i, Double.MAX_VALUE);
        }
        while (!q.isEmpty()) {
            int v = q.delMin();
            for (DirectedWeightedEdge e : g.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedWeightedEdge e) {
        int v = e.getFrom();
        int w = e.getTo();
        if (distTo[w] > distTo[v] + e.getWeight()) {
            edgeTo[w] = v;
            distTo[w] = distTo[v] + e.getWeight();
            if (q.hasKey(w)) {
                q.decreaseKey(w, distTo[w]);
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
        Dijkstra k = new Dijkstra(g);
        System.out.println(Arrays.toString(k.distTo));
        System.out.println(Arrays.toString(k.edgeTo));
    }
}
