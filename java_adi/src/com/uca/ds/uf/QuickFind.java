package com.uca.ds.uf;

import java.util.Arrays;

public class QuickFind extends UnionFind {
    protected QuickFind(int n) {
        super(n);
    }

    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);
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

    @Override
    public void union(int v, int w) {
        for (int i = 0; i < n; i++) {
            if (id[i] == id[v]) {
                id[i] = w;
            }
        }
    }

    @Override
    public boolean find(int v, int w) {
        return id[v] == id[w];
    }
}
