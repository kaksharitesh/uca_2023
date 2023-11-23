package com.uca.ds;

import java.util.*;

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
        root = del(root, k);
    }
    private Node del(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.k);
        if (cmp == 0) {
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;
            Node t = x;
            x = getMax(t.left);
            x.left = delMax(t.left);
            x.right = t.right;
            return x;
        } else if (cmp < 0)
            return del(x.left, key);
        return del(x.right, key);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public K getMin() {
        if(root==null) return null;
        return getMin(root).k;
    }

    public Node getMin(Node n){
        if(n.left==null) return n;
        return getMin(n.left);
    }

    public void delMin() {
        if (root == null)
            return;
        root = delMin(root);
    }
    private Node delMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = delMin(x.left);
        x.c = 1 + sizeOf(x.left) + sizeOf(x.right);
        return x;
    }

    public K getMax() {
        if(root==null) return null;
        return getMax(root).k;
    }
    private Node getMax(Node x) {
        if (x.right == null)
            return x;
        return getMax(x.right);
    }


    public void delMax() {
        if (root == null)
            return;
        root = delMax(root);
    }
    private Node delMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = delMax(x.right);
        x.c = 1 + sizeOf(x.left) + sizeOf(x.right);
        return x;
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

    public K getCeling(K key) {
        Node t = getCeling(root, key);
        return t == null ? null : t.k;
    }

    private Node getCeling(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.k);
        if (cmp == 0)
            return x;
        else if (cmp > 0)
            return getCeling(x.right, key);

        Node t = getCeling(x.left, key);
        return t == null ? x : t;
    }

    public K getFloor(K key) {
        Node t = getFloor(root, key);
        return t == null ? null : t.k;
    }

    private Node getFloor(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.k);
        if (cmp == 0)
            return x;
        else if (cmp < 0)
            return getFloor(x.left, key);

        Node t = getFloor(x.right, key);
        return t == null ? x : t;
    }



    public class Node {
        private final K k;
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

    public K getFirstCommonAncestor(K k1, K k2) {
        Node ancestor = getFirstCommonAncestor(root, k1, k2, null);
        return ancestor == null ? null : ancestor.k;
    }

    private Node getFirstCommonAncestor(Node node, K k1, K k2, Node parent) {
        int cmp1 = k1.compareTo(node.k);
        int cmp2 = k2.compareTo(node.k);
        if (cmp1 > 0 && cmp2 > 0)
            return getFirstCommonAncestor(node.right, k1, k2, node);
        if (cmp1 < 0 && cmp2 < 0)
            return getFirstCommonAncestor(node.left, k1, k2, node);
        if (cmp1 == 0 || cmp2 == 0)
            return parent;
        return node;
    }

    public void topView() {
        topView(root);
        System.out.println();

    }

    private void topView(Node n) {
        java.util.Set<Integer> s = new HashSet<>();
        Queue<NodeData> q = new LinkedList<>();
        q.add(new NodeData(n, 0));
        while (!q.isEmpty()) {
            NodeData temp = q.poll();
            Node tempNode = temp.node;
            int d = temp.d;
            if (!s.contains(temp.d)) {
                System.out.print(tempNode.k + " ");
                s.add(d);
            }
            if (tempNode.left != null)
                q.add(new NodeData(tempNode.left, d - 1));
            if (tempNode.right != null)
                q.add(new NodeData(tempNode.right, d + 1));
        }

    }

    private class NodeData {
        Node node;
        int d;

        public NodeData(Node node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    public void convertToDLink() {
        List<Node> ends = convertToDLink(root);
        if (ends.get(1) != null) {
            ends.get(0).left = ends.get(1);
            ends.get(1).right = ends.get(0);
            root = ends.get(0);
        }
    }

    private List<Node> convertToDLink(Node n) {
        List<Node> ends = new ArrayList<>(2);
        if (n != null) {
            List<Node> eLeft = convertToDLink(n.left);
            n.left = null;
            if (eLeft.size() > 1) {
                n.left = eLeft.get(1);
                eLeft.get(1).right = n;
            }

            List<Node> eRight = convertToDLink(n.right);
            n.right = null;
            if (!eRight.isEmpty()) {
                n.right = eRight.get(0);
                eRight.get(0).left = n;
            }

            Node l = eLeft.isEmpty() ? n : eLeft.get(0);
            Node r = eRight.size() < 2 ? n : eRight.get(1);
            ends.add(l);
            ends.add(r);
        }
        return ends;
    }

    public void printDLink() {
        Node t = root;
        System.out.println(t.k);
        t = t.right;
        while (t.k != root.k) {
            System.out.println(t.k);
            t = t.right;
        }
    }
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node x) {
        if (x == null)
            return;
        inOrder(x.left);
        System.out.print(x.k + " ");
        inOrder(x.right);
    }


    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        assert bst.isEmpty();
        bst.insert(5, "five");
        bst.insert(1, "five");
        bst.insert(2, "two");
        bst.insert(3, "three");
        bst.insert(4, "five");
        bst.insert(9, "nine");
        bst.insert(8, "eight");
        bst.topView();
        bst.inOrder();

        assert 5 == bst.getFirstCommonAncestor(3,9);


        bst.delete(5);
        bst.inOrder();
        bst.insert(5, "five");
        bst.insert(10, "ten");
        bst.insert(-1, "neg one");
        bst.inOrder();

        assert 10==bst.getMax();
        assert -1 == bst.getMin();

        assert !bst.isEmpty();

        bst.delMin();
        bst.delMax();

        assert 9==bst.getMax();
        assert 1 == bst.getMin();
        assert 3 == bst.getCeling(3);
        assert 3 == bst.getFloor(3);



        bst.convertToDLink();
        bst.printDLink();

    }


}
