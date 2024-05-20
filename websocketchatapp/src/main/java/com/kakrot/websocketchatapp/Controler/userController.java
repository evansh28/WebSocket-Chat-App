package com.kakrot.websocketchatapp.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakrot.websocketchatapp.Models.User;
import com.kakrot.websocketchatapp.Services.userService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class userController {

    @Autowired
    private final userService userService;

    @MessageMapping("/user.adduser")
    @SendTo("/user/topic")
    public User addUsers(@Payload User user){
        userService.saveUser(user);
        return user;
    }


    @MessageMapping("/user.disconnectuser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.desconnectUser(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUser(){

        return ResponseEntity.ok(userService.fingConnectedUsers());
        
    }
    
}
