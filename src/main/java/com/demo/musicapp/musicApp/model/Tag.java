package com.demo.musicapp.musicApp.model;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Tag {
    @ManyToMany( fetch = FetchType.EAGER, mappedBy="tag", cascade = CascadeType.ALL)
    private Set<Playlist> playlists = new TreeSet<>(Comparator.comparing(Playlist::getNoOfViews));

    @Id
    private int tagId;

    public int getTagId() {
        return tagId;
    }

    private String tagName;

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
