package com.uca.ds;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node root;

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        for (int i = 0; i < 10; i++)
            tree.insert(i, "No:" + i);
        System.out.println(tree.height());
        for (int i = 0; i < 10; i++)
            System.out.println(tree.get(i));
        System.out.println(tree.get(10));
        System.out.println(tree.size());
        tree.print();
    }

    public void print() {
        System.out.println("*******************");
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node t = q.poll();
            if (t == null) {
                System.out.println();
                if(!q.isEmpty())
                    q.add(null);
            } else {
                System.out.print(t.k + "\t");
                if (t.left != null)
                    q.add(t.left);
                if (t.right != null)
                    q.add(t.right);
            }
        }
    }

    private V get(K k) {
        Node t = get(root, k);
        return t == null ? null : t.v;
    }

    public Node get(Node n, K k) {
        if (n == null) return null;
        int cmp = k.compareTo(n.k);
        if (cmp > 0)
            return get(n.right, k);
        if (cmp < 0) return get(n.left, k);
        return n;

    }

    public void insert(K k, V v) {
        root = insert(k, v, root);
        root.color = BLACK;
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
        if (getColor(n.left) == BLACK && getColor(n.right) == RED) {
            n = leftRotate(n);
        }
        if (getColor(n.left) == RED && getColor(n.left.left) == RED) {
            n = rightRotate(n);
        }
        if (getColor(n.left) == RED && getColor(n.right) == RED) {
            n = flipColor(n);
        }
        n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
        return n;
    }

    private Node flipColor(Node n) {
        n.left.color = BLACK;
        n.right.color = BLACK;
        n.color = RED;
        return n;
    }

    private int sizeOf(Node n) {
        return n == null ? 0 : n.N;
    }

    public int size() {
        return sizeOf(root);
    }

    private Node leftRotate(Node n) {
        Node t = n.right;
        n.right = t.left;
        t.left = n;
        t.color = n.color;
        n.color = RED;
        t.N = n.N;
        n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
        return t;
    }

    private Node rightRotate(Node n) {
        Node t = n.left;
        n.left = t.right;
        t.right = n;
        t.color = n.color;
        n.color = RED;
        t.N = n.N;
        n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
        return t;
    }

    private boolean getColor(Node n) {
        return n == null ? BLACK : n.color;
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public class Node {
        private final K k;
        private V v;
        private Node left;
        private Node right;

        private boolean color;

        private int N;


        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
            this.color = RED;
            this.N = 1;
        }
    }

}
