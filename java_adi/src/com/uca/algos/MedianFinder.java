package com.uca.algos;

import com.uca.ds.MyHeap;

public class MedianFinder {
    private final MyHeap minHeap;
    private final MyHeap maxHeap;

    public MedianFinder(int capacity) {
        minHeap = new MyHeap(capacity, false);
        maxHeap = new MyHeap(capacity, true);
    }

    public void insert(int key) {
        if (minHeap.isEmpty()) {
            minHeap.insert(key);
            return;
        }

        if (key > minHeap.getMin()) {
            minHeap.insert(key);
        } else {
            maxHeap.insert(key);
        }
        reBalance();
    }

    private void reBalance() {
        if (Math.abs(minHeap.size() - maxHeap.size()) <= 1)
            return;
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.insert(minHeap.delMin());
        } else {
            minHeap.insert(maxHeap.delMax());
        }
    }

    public int getMedian() {
        if ((maxHeap.size() + minHeap.size()) % 2 == 0) {
            return (maxHeap.getMax() + minHeap.getMin()) / 2;
        }
        return minHeap.size() > maxHeap.size() ? minHeap.getMin() : maxHeap.getMax();
    }

    @Override
    public String toString() {
        return "max heap :" + maxHeap + "\n" + "min heap : " + minHeap;
    }
}
