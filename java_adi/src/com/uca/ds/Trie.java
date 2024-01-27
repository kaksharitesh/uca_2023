package com.uca.ds;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("man", "1");
        trie.insert("mad", "2");
        trie.insert("men", "3");
        trie.insert("mandate", "4");
        trie.insert("sun", "5");
        trie.insert("sat", "6");
        trie.insert("sam", "7");
        assert trie.search("man").equals("1");
        assert trie.search("apple") == null;
        assert trie.startsWith("ma");
        assert !trie.startsWith("mane");
        assert trie.startsWith("mand");
        trie.delete("mandate");
        assert trie.startsWith("ma");
        assert !trie.startsWith("mane");
        assert !trie.startsWith("mand");
        assert trie.search("mandate") == null;

    }

    public void insert(String key, String value) {
        TrieNode t = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (t.children[index] == null) {
                t.children[index] = new TrieNode();
            }
            t = t.children[index];
        }
        t.value = value;
    }

    public String search(String key) {
        TrieNode t = searchKey(key);
        return t == null ? null : t.value;
    }

    public boolean startsWith(String sequence) {
        return searchKey(sequence) != null;
    }

    private TrieNode searchKey(String key) {
        TrieNode t = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (t.children[index] == null) {
                return null;
            }
            t = t.children[index];
        }
        return t;
    }

    public void delete(String key) {
        delete(root, key, 0);
    }

    private boolean delete(TrieNode n, String key, int level) {
        if(n==null) return false;  //key not found
        //case 1 key is found -- level == len(key)

        if(level == key.length()){
            if(n.value != null){
                n.value = null;
                return isDeletable(n);   //return true if I don't have any children
            }
            return false;  //not a valid key to be deleted.
        }
        //case 2 iteration is still not reached to leaf node -- path
       int index = key.charAt(level) - 'a';
       if(delete(n.children[index], key, level+1)){
           n.children[index] = null;
           return isDeletable(n);  //delete me If I have no children and I dont have any value.
       }
       return false;

    }

    private boolean isDeletable(TrieNode n) {
        if (n.value != null) return false;
        for (TrieNode t : n.children) {
            if (t != null) return false;
        }
        return true;
    }

    private static class TrieNode {
        private String value;   //for a valid key, this is not null
        private final TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

}
