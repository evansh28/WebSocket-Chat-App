package com.kakrot.websocketchatapp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kakrot.websocketchatapp.Models.Status;
import com.kakrot.websocketchatapp.Models.User;

public interface userRepo extends MongoRepository<User, String>{

    List<User> findAllByStatus(Status status);
    
}
