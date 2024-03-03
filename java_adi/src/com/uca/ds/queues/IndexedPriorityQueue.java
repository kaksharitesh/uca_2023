package com.uca.ds.queues;

import java.util.Arrays;
import java.util.Random;

public class IndexedPriorityQueue {
    private final int[] pq;
    private final int[] qp;
    private final Double[] keys;

    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public IndexedPriorityQueue(int n) {
        pq = new int[n];
        qp = new int[n];
        keys = new Double[n];
        Arrays.fill(qp, -1);
    }

    public static void main(String[] args) {
        IndexedPriorityQueue q = new IndexedPriorityQueue(10);
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            q.insert(i, r.nextInt(100));
        }
        System.out.println(q);
        System.out.println(q.getMin());
        System.out.println(q.hasKey(6));
        q.decreaseKey(6, -1);
        System.out.println(q);
        for (int i = 0; i < 10; i++) {
            System.out.println(q.delMin());
        }
    }

    public void insert(int key, double weight) {
        pq[size] = key;
        qp[key] = size;
        keys[key] = weight;
        swim(key);
        size++;
    }

    private void swim(int key) {
        if (key == 0) return;
        int p = key / 2;
        if (keys[pq[p]] > keys[pq[key]]) {
            swap(p, key);
            swim(p);
        }
    }

    private void swap(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public boolean hasKey(int key) {
        return qp[key] != -1;
    }

    public void decreaseKey(int key, double weight) {
        keys[key] = weight;
        swim(qp[key]);
    }

    public int getMin() {
        return pq[0];
    }

    public int delMin() {
        int minKey = getMin();
        qp[minKey] = -1;
        keys[minKey] = null;
        swap(0, size - 1);
        size--;
        sink(0);
        return minKey;
    }

    private void sink(int i) {
        if (i >= size) return;
        int left = 2 * i;
        int right = 2 * i + 1;
        int min = i;
        if (left < size && keys[pq[left]] < keys[pq[min]]) {
            min = left;
        }
        if (right < size && keys[pq[right]] < keys[pq[min]]) {
            min = right;
        }
        if (min != i) {
            swap(min, i);
            sink(min);
        }

    }

    @Override
    public String toString() {
        return "IndexedPriorityQueue{" +
                "\n pq  =" + Arrays.toString(pq) +
                ",\n qp  =" + Arrays.toString(qp) +
                ",\n keys=" + Arrays.toString(keys) +
                "\n}";
    }
}
