package com.uca.ds.graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prims {

    public Queue<WeightedEdge> mst = new LinkedList<>();
    private PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();

    private boolean[] marked;

    public Prims(WeightedGraph g) {
        marked = new boolean[g.V];
        visit(0, g);

        while (!pq.isEmpty() && mst.size() < g.V) {  //V
            WeightedEdge e = pq.poll(); //log E
            int to = e.either();
            int from = e.other(to);
            if (marked[to] && marked[from]) {
                continue;  //cycle
            }
            mst.add(e);
            if (!marked[to]) visit(to, g);
            if (!marked[from]) visit(from, g);
        }
    }

    private void visit(int v, WeightedGraph g) {
        marked[v] = true;
        for (WeightedEdge e : g.adj(v)) {
            pq.add(e);
        }
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph(8);
        g.addEdge(new WeightedEdge(0,7,0.16));
        g.addEdge(new WeightedEdge(2,3,0.17));
        g.addEdge(new WeightedEdge(1,7,0.19));
        g.addEdge(new WeightedEdge(0,2,0.26));
        g.addEdge(new WeightedEdge(5,7,0.28));
        g.addEdge(new WeightedEdge(1,3,0.29));
        g.addEdge(new WeightedEdge(1,5,0.32));
        g.addEdge(new WeightedEdge(2,7,0.34));
        g.addEdge(new WeightedEdge(4,5,0.35));
        g.addEdge(new WeightedEdge(1,2,0.36));
        g.addEdge(new WeightedEdge(4,7,0.37));
        g.addEdge(new WeightedEdge(0,4,0.38));
        g.addEdge(new WeightedEdge(6,2,0.40));
        g.addEdge(new WeightedEdge(3,6,0.52));
        g.addEdge(new WeightedEdge(6,0,0.58));
        g.addEdge(new WeightedEdge(6,4,0.93));
        Prims k = new Prims(g);
        System.out.println(k.mst);
    }
}
