package com.demo.musicapp.musicApp.dao;

import com.demo.musicapp.musicApp.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<Tag, Integer> {

}
