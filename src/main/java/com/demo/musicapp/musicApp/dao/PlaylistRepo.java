package com.demo.musicapp.musicApp.dao;

import com.demo.musicapp.musicApp.model.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepo extends CrudRepository<Playlist, Integer> {

}
