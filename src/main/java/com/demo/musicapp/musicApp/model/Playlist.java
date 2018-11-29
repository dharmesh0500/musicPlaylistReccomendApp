package com.demo.musicapp.musicApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Playlist implements Comparator<Playlist> {

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="playlists", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList <>();

    public List <User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

    @Id
    private int playlistId;
    private String playlistName;
    private int noOfViews ;

    @ManyToMany
    @JoinTable(name = "playlist_tag",
    joinColumns = { @JoinColumn(name="fk_playlists")},
    inverseJoinColumns = { @JoinColumn(name = "fk_tag")})
    private List<Tag> tag = new ArrayList<>();

    public List <Tag> getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag.add(tag);
    }

    public int getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(int noOfViews) {
        this.noOfViews = noOfViews;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @Override
    public int compare(Playlist o1, Playlist o2) {
        if(o1.getNoOfViews()>o2.getNoOfViews())
            return 1;
        else if(o1.getNoOfViews()==o2.getNoOfViews())
            return 0;
        else return -1;
    }
}
