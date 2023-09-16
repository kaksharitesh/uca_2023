package com.uca.ds;

public class MyHeap {
    private final int[] a;
    private final boolean reverse;
    private int size = 0;

    public MyHeap(int capacity) {
        this(capacity, false);
    }

    public MyHeap(int capacity, boolean reverse) {
        a = new int[capacity];
        this.reverse = reverse;
    }

    public int size() {
        return size;
    }

    public void insert(int key) {
        a[size] = key;
        swim(size);
        size++;
    }

    private void swim(int i) {
        if (i == 0) {
            return;
        }
        int p = i / 2;
        if (compare(a[i], a[p]) < 0) {
            swap(i, p);
            swim(p);
        }
    }

    public int getMin() {
        if (reverse) {
            throw new RuntimeException("Are you stupid I am max heap");
        }
        if (isEmpty()) {
            throw new IllegalStateException("I am empty");
        }
        return a[0];
    }

    public int getMax() {
        if (!reverse) {
            throw new RuntimeException("Are you stupid I am min heap");
        }
        if (isEmpty()) {
            throw new IllegalStateException("I am empty");
        }
        return a[0];
    }

    public int delMin() {
        int temp = getMin();
        delTop();
        return temp;
    }

    public int delMax() {
        int temp = getMax();
        delTop();
        return temp;
    }

    private void delTop() {
        swap(0, size - 1);
        size--;
        sink(0);
    }

    private int compare(int i, int j) {
        if (reverse)
            return j - i;
        return i - j;
    }

    private void sink(int i) {
        int candidate = i;
        int L = 2 * i;
        if (L >= size) {
            return;
        }
        if (compare(a[candidate], a[L]) > 0) {
            candidate = L;
        }
        int R = 2 * i + 1;
        if (R < size && compare(a[candidate], a[R]) > 0) {
            candidate = R;
        }
        if (candidate != i) {
            swap(candidate, i);
            sink(candidate);
        }
    }

    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("size=" + size + " : [");
        for (int i = 0; i < size; i++) {
            sb.append(a[i]).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

}
