package com.uca.ds.uf;

import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {
    protected final int[] sz;

    protected WeightedQuickUnion(int n) {
        super(n);
        sz = new int[n];
        Arrays.fill(sz, 1);
    }

    public static void main(String[] args) {
        WeightedQuickUnion qf = new WeightedQuickUnion(10);
        assert !qf.find(0, 2);
        assert !qf.find(1, 2);
        assert !qf.find(1, 0);
        qf.union(1, 0);
        qf.union(1, 2);
        qf.union(1, 3);
        qf.union(1, 4);
        System.out.println(Arrays.toString(qf.id));
        System.out.println(Arrays.toString(qf.sz));
        assert qf.find(0, 2);
        assert qf.find(1, 2);
        assert qf.find(1, 0);
        assert qf.find(2, 4);
    }

    @Override
    public void union(int v, int w) {
        int x = root(v);
        int y = root(w);
        if (sz[x] > sz[y]) {
            id[y] = x;
            sz[x] += sz[y];
        } else {
            id[x] = y;
            sz[y] += sz[x];
        }
    }
}
