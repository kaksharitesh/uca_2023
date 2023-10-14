package com.uca.ds;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    public void insert(K k, V v) {
        root = insert(k, v, root);
    }

    private Node insert(K k, V v, Node n) {
        if (n == null) return new Node(k, v);
        int cmp = k.compareTo(n.k);
        if (cmp == 0) {
            n.v = v;   //update query
        } else if (cmp > 0) {
            n.right = insert(k, v, n.right);
        } else {
            n.left = insert(k, v, n.left);
        }
        n.c = 1 + sizeOf(n.left) + sizeOf(n.right);
        return n;
    }

    private int sizeOf(Node n) {
        return n == null ? 0 : n.c;
    }

    public V search(K k) {
        Node n = search(k, root);
        if (n == null) return null;
        return n.v;
    }

    private Node search(K k, Node n) {
        if (n == null) return null;
        int cmp = k.compareTo(n.k);
        if (cmp == 0) return n;
        if (cmp > 0) return search(k, n.right);
        return search(k, n.left);

    }

    public int size() {
        return sizeOf(root);
    }

    public void delete(K k) {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public K getMin() {
        return null;
    }

    public void delMin() {

    }

    public K getMax() {
        return null;
    }

    public void delMax() {

    }

    public int rank(K k) {
        if (search(k) == null) return -1;
        return rank(k, root);
    }

    public int rank(K k, Node n) {
        if(n==null) return -1; //dead code
        int cmp = k.compareTo(n.k);
        if(cmp == 0) return sizeOf(n.left);
        if(cmp <0) return rank(k, n.left);
        return 1 + sizeOf(n.left) + rank(k, n.right);
    }


    private class Node {
        private K k;
        private V v;
        private Node left;
        private Node right;

        private int c;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
            this.c = 1;
        }
    }

}
