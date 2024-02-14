package com.uca.ds.graphs;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private Node first;
    private Node last;

    public void insert(T t) {
        Node temp = new Node(t);
        if (first == null) {
            first = temp;
        } else {
            last.next = temp;
        }
        last = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node temp = first;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                Node t = temp;
                temp = temp.next;
                return t.value;
            }
        };
    }


    private class Node {
        private final T value;
        private Node next;

        public Node(T t){
            this.value = t;
        }
    }
}
