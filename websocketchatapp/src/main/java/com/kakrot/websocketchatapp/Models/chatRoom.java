package com.kakrot.websocketchatapp.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class chatRoom {

    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    
}
