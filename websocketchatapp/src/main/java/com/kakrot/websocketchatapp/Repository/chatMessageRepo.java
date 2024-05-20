package com.kakrot.websocketchatapp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kakrot.websocketchatapp.Models.chatMessage;

public interface chatMessageRepo extends MongoRepository<chatMessage, String>{

    List<chatMessage> findByChatId(String s);
    
}
