package com.kakrot.websocketchatapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakrot.websocketchatapp.Models.chatRoom;
import com.kakrot.websocketchatapp.Repository.chatRoomRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class chatRoomService {

    @Autowired
    private final chatRoomRepo chatRoomRepo;

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist) {

        return chatRoomRepo.findBySenderIdAndRecipientId(senderId, recipientId).map(chatRoom::getChatId).or(() -> {
            if (createNewRoomIfNotExist) {

                var chatId = createChatId(senderId, recipientId);
                return Optional.of(chatId);

            }
            return Optional.empty();
        });

    }

    private String createChatId(String senderId, String recipientId) {

        var chatId = String.format("%s_%s", senderId, recipientId);

        chatRoom senderRecipient = chatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        chatRoom recipientSender = chatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepo.save(senderRecipient);
        chatRoomRepo.save(recipientSender);

        return chatId;

    }

}
