package com.uca.ds;

import java.util.LinkedList;
import java.util.Queue;

public class WeakAVL {

    private Node root;

    public static void main(String[] args) {
        WeakAVL tree = new WeakAVL();
        for (int i = 0; i < 510; i++) {
            tree.insert(i, i);
        }
        System.out.println(tree.height());
        //tree.printTree();
    }

    private void printTree(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node t = q.poll();
            if(t==null){
                System.out.println();
                if(!q.isEmpty()){
                    q.add(null);
                }
            }else{
                System.out.print(t.key+"("+height(t)+")  ");
                if(t.left!=null) q.add(t.left);
                if(t.right!=null) q.add(t.right);
            }
        }
    }

    public void insert(int k, int v) {
        root = insert(k, v, root);
    }

    private Node insert(int k, int v, Node n) {
        if (n == null) return new Node(k, v);

        if (k == n.key) {
            n.data = v;   //update query
        } else if (k > n.key) {
            n.right = insert(k, v, n.right);
        } else {
            n.left = insert(k, v, n.left);
        }
        n.h = 1 + Math.max(height(n.left), height(n.right));

        int balance = balance(n);
        if (balance > 1) {
            if (balance(n.left) < 0) {
                n.right = leftRotate(n.right);
            }
            n = rightRotate(n);
        }
        if (balance < -1) {
            if (balance(n.right) > 0) {
                n.right = rightRotate(n.right);
            }
            n = leftRotate(n);
        }

        return n;
    }

    private Node rightRotate(Node n) {
        Node t = n.left;
        n.left = t.right;
        t.right = n;
        n.h = 1  + Math.max(height(n.left), height(n.right));
        t.h = 1  + Math.max(height(t.left), height(t.right));
        return t;
    }

    private Node leftRotate(Node n) {
        Node t = n.right;
        n.right = t.left;
        t.left = n;
        n.h = 1  + Math.max(height(n.left), height(n.right));
        t.h = 1  + Math.max(height(t.left), height(t.right));
        return t;
    }


    private int balance(Node n) {
        return height(n.left) - height(n.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return 0;
        return n.h;
    }

    private static class Node {
        private final int key;
        private int data;
        private Node left;
        private Node right;

        private int h;

        public Node(int k, int v) {
            this.key = k;
            this.data = v;
            this.left = null;
            this.right = null;
            this.h = 1;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }


}