package com.kakrot.websocketchatapp.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kakrot.websocketchatapp.Models.chatMessage;
import com.kakrot.websocketchatapp.Models.chatNotification;
import com.kakrot.websocketchatapp.Services.chatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class chatMessageController {

    @Autowired
    private final chatMessageService chatMessageService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<chatMessage>> findChatMessages(@PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }

    @MessageMapping("/chat")
    public void processMassage(@Payload chatMessage chatMessage) {

        chatMessage saveMsg = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/que/messages",
                chatNotification.builder()
                        .id(saveMsg.getId())
                        .senderId(saveMsg.getSenderId())
                        .recipientId(saveMsg.getRecipientId())
                        .content(saveMsg.getContent())
                        .build());
    }

}
