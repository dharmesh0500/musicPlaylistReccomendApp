package com.demo.musicapp.musicApp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    private int userId;
    private String userName;

    @ManyToMany
    @JoinTable(name = "user_playlist",
            joinColumns = { @JoinColumn(name="fk_playlists")},
            inverseJoinColumns = { @JoinColumn(name = "fk_users")})
    private List<Playlist> playlists = new ArrayList<>();

    public List <Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }


}
