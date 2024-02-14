package com.uca.ds.graphs;

import com.uca.ds.uf.WPCQuickUnion;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Kruskal {

    public Queue<WeightedEdge> mst = new LinkedList<>();

    public Kruskal(WeightedGraph g){
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        //fill all the edges
        for(int i = 0;i<g.V;i++){
            for(WeightedEdge e : g.adj(i)){
                pq.add(e);  //VlogE
            }
        }

        WPCQuickUnion uf = new WPCQuickUnion(g.V);
        while(!pq.isEmpty() && mst.size() < g.V){ //V-1
            WeightedEdge e = pq.poll();   //log e
            int to = e.either();
            int from = e.other(to);
            if(!uf.find(to, from)){ // no cycle is formed using this edge
                mst.add(e);
                uf.union(to, from);
            }
        }

        assert mst.size() == g.V-1; //not a valid graph - graph is disconnected.
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
        Kruskal k = new Kruskal(g);
        System.out.println(k.mst);
    }
}
