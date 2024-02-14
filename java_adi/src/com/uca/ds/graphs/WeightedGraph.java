package com.uca.ds.graphs;

public class WeightedGraph {

   private final Bag<WeightedEdge>[] adj;
   public final int V;
   private int E;

   public WeightedGraph(int V){
       this.V = V;
       adj = new Bag[V];
       for(int i = 0;i<V;i++)
           adj[i] = new Bag<>();
   }

   public void addEdge(WeightedEdge e){
       int to = e.either();
       int from = e.other(to);
       adj[to].insert(e);
       adj[from].insert(e);
       E++;
   }

   public Iterable<WeightedEdge> adj(int v){
       return adj[v];
   }
}
