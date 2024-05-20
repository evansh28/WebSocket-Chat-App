package com.kakrot.websocketchatapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakrot.websocketchatapp.Models.Status;
import com.kakrot.websocketchatapp.Models.User;
import com.kakrot.websocketchatapp.Repository.userRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userService {

    @Autowired
    private final userRepo userRepo;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepo.save(user);

    }

    public void desconnectUser(User user){
        var storedUser = userRepo.findById(user.getNickName()).orElse(null);

        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepo.save(storedUser);
        }

    }

    public List<User> fingConnectedUsers() {


        return userRepo.findAllByStatus(Status.ONLINE);
    }
    
}
