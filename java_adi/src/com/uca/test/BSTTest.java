package com.uca.test;

import com.uca.ds.BST;

public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.insert(5, "five");
        bst.insert(3, "three");
        bst.insert(4, "four");
        bst.insert(2, "two");
        bst.insert(1, "one");
        bst.insert(7, "seven");
        bst.insert(6, "six");
        bst.insert(8, "eight");
        assert bst.search(2).equals("two");
        assert bst.search(9) == null;

        assert  bst.rank(5)==4;
        assert  bst.rank(7)==6;
        assert  bst.rank(2)==1;
        assert  bst.rank(9)==-1;
        System.out.println("test case passed");
    }
}
