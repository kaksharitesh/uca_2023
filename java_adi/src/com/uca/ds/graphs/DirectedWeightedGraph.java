package com.uca.ds.graphs;

public class DirectedWeightedGraph {

    private final Bag<DirectedWeightedEdge>[] adj;
    public final int V;
    private int E;

    public DirectedWeightedGraph(int V) {
        this.V = V;
        adj = new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public void addEdge(DirectedWeightedEdge e) {
        int from = e.getFrom();
        adj[from].insert(e);
        E++;
    }

    public Iterable<DirectedWeightedEdge> adj(int v) {
        return adj[v];
    }
}
