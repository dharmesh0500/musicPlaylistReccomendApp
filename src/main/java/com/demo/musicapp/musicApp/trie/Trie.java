package com.demo.musicapp.musicApp.trie;

import com.demo.musicapp.musicApp.model.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Trie {
    static final int ALPHABET_SIZE = 26;
    final Node root = new Node();

    public void add(String str, String value) {

        Node curr = root;
        str = str.toLowerCase();
        //System.out.println("Inside Trie==Add ===STR===>>"+str);
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                    curr.children[idx].nodeName = str;
                    curr.children[idx].value = value;
                }
                curr = curr.children[idx];

        }
        curr.isEnd = true;
    }

    public void add(String str, Set<Playlist> value) {
        Node curr = root;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                    curr.children[idx].nodeName = str;
                    curr.children[idx].playlists = value;
                }
                curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    public Node getNode(String str) {
        Node node = root;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) - 'a' >=0 && str.charAt(i) - 'a'<26) {
                Node child = node.children[str.charAt(i) - 'a'];
                if (child == null) {
                    return null;
                }
                node = child;
            }
        }
        return node;
    }

    public boolean contains(String str) {
        Node node = getNode(str);
        return node != null && node.isEnd;
    }

    public List<Node> prefixedWords(String str) {
        Node curr = getNode(str);
        List<Node> prefixedWords = new ArrayList<>();
        DFS(curr, str, prefixedWords);
        return prefixedWords;
    }

     void DFS(Node root, String prefix, List<Node> list) {
        if (root == null) {
            return;
        }

        if (root.isEnd) {
            list.add(getNode(prefix));
        }
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                DFS(root.children[i], prefix + (char) (i + 'a'), list);
            }
        }
    }
}
