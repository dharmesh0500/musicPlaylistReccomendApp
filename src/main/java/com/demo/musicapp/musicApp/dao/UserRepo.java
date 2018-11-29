package com.demo.musicapp.musicApp.dao;

import com.demo.musicapp.musicApp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

}
