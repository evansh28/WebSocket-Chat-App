package com.kakrot.websocketchatapp.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kakrot.websocketchatapp.Models.chatRoom;

public interface chatRoomRepo extends MongoRepository<chatRoom, String>{

    Optional<chatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
    
}
