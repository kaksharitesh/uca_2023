package com.uca.test;

import com.uca.algos.MedianFinder;
import com.uca.ds.heap.MyHeap;

import java.util.*;

public class MyHeapTest {
    private static final int capacity = 100;

    public static void main(String[] args) {
        testMaxHeap();
        testMinHeap();
        testMedianFinder();
        System.out.println("All test cases passed!!");
    }

    private static void testMinHeap() {
        System.out.println("Testing min heap");

        MyHeap minHeap = new MyHeap(capacity);

        Random r = new Random();
        int[] a = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            int temp = (int) (r.nextFloat() * capacity * 10);
            a[i] = temp;
            minHeap.insert(temp);
        }
        Arrays.sort(a);
        for (int i = 0; i < capacity; i++) {
            assert a[i] == minHeap.delMin();
        }

    }

    private static void testMaxHeap() {
        System.out.println("Testing max heap");
        MyHeap maxHeap = new MyHeap(capacity, true);

        Random r = new Random();
        int[] a = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            int temp = (int) (r.nextFloat() * capacity * 10);
            a[i] = temp;
            maxHeap.insert(temp);
        }
        Arrays.sort(a);
        for (int i = 0; i < capacity; i++) {
            assert a[capacity - 1 - i] == maxHeap.delMax();
        }
    }

    private static void testMedianFinder() {
        System.out.println("Testing median finder");
        MedianFinder medianFinder = new MedianFinder(capacity);

        Random r = new Random();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            int temp = (int) (r.nextFloat() * capacity * 10);
            a.add(temp);
            medianFinder.insert(temp);
            Collections.sort(a);
            assert getMedian(a) == medianFinder.getMedian();
        }
    }

    private static int getMedian(List<Integer> a) {
        if (a.size() % 2 == 1) {
            return a.get(a.size() / 2);
        }
        return (a.get(a.size() / 2 - 1) + a.get(a.size() / 2)) / 2;
    }
}
