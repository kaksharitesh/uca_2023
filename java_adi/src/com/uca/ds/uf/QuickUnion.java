package com.uca.ds.uf;

import java.util.Arrays;

public class QuickUnion extends UnionFind {
    protected QuickUnion(int n) {
        super(n);
    }

    @Override
    public void union(int v, int w) {
        int x = root(v);
        int y = root(w);
        id[x] = y;
    }

    @Override
    public boolean find(int v, int w) {
        return root(v) == root(w);
    }

    protected int root(int v) {
        while (id[v] != v) {
            v = id[v];
        }
        return v;
    }

    public static void main(String[] args) {
        QuickUnion qf = new QuickUnion(10);
        assert !qf.find(0, 2);
        assert !qf.find(1, 2);
        assert !qf.find(1, 0);
        qf.union(1, 0);
        qf.union(1, 2);
        System.out.println(Arrays.toString(qf.id));
        assert qf.find(0, 2);
        assert qf.find(1, 2);
        assert qf.find(1, 0);
    }
}
