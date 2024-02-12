package com.uca.ds.uf;

import java.util.Arrays;

public class WPCQuickUnion extends WeightedQuickUnion {
    //weighted quick union with path compression
    protected WPCQuickUnion(int n) {
        super(n);
    }

    public static void main(String[] args) {
        WPCQuickUnion qf = new WPCQuickUnion(10);
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
    public int root(int v) {
        while (id[v] != v) {
            id[v] = id[id[v]]; //path compression
            v = id[v];
        }
        return v;
    }
}
