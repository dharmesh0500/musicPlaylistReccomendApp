package com.demo.musicapp.musicApp.trie;

import com.demo.musicapp.musicApp.model.Playlist;

import java.util.Set;

public class Node {
    final Node[] children = new Node[Trie.ALPHABET_SIZE];
    boolean isEnd = false;
    public String nodeName = "";
    public String value = "";
    public Set<Playlist> playlists;
}
