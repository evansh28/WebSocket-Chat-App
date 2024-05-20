package com.kakrot.websocketchatapp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakrot.websocketchatapp.Models.chatMessage;
import com.kakrot.websocketchatapp.Repository.chatMessageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class chatMessageService {

    @Autowired
    private final chatMessageRepo chatMessageRepo;

    @Autowired
    private final chatRoomService chatRoomService;

    public chatMessage save(chatMessage message) {

        var chatId = chatRoomService.getChatRoomId(message.getSenderId(), message.getRecipientId(), true).orElseThrow();

        message.setChatId(chatId);
        chatMessageRepo.save(message);
        return message;
    }

    public List<chatMessage> findChatMessage(String senderId, String recipientId){

        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
        
    }

}
