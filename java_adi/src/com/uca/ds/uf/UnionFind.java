package com.uca.ds.uf;

public abstract class UnionFind {
    protected final int[] id;
    protected final int n;


    protected UnionFind(int n) {
        this.n = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public abstract void union(int v, int w);

    public abstract boolean find(int v, int w);
}
