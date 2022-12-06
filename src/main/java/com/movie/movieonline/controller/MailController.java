package com.movie.movieonline.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.User;
import com.movie.movieonline.service.UserService;
import com.movie.movieonline.util.MessageResponse;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    @PutMapping("password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody User username){

        User user = userService.loadUserByUsername(username.getUsername());

        int length = 6;
        String generatedPassword = RandomStringUtils.random(length, true, true);
        
        userService.changePassword(generatedPassword, user.getId());

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("moviesuperduperpro@gmail.com");
        message.setTo(user.getUsername()); 
        message.setSubject("New Password"); 
        message.setText("This is your new password: " + generatedPassword);
        emailSender.send(message);

        return new ResponseEntity<MessageResponse> (new MessageResponse("New Password Sent"), HttpStatus.OK);
    }

    @PutMapping("password/change")
    public ResponseEntity<?> changePassword(@RequestBody User username){

        User user = userService.loadUserByUsername(username.getUsername());

        int length = 6;
        String generatedPassword = RandomStringUtils.random(length, true, true);
        
        userService.changePassword(generatedPassword, user.getId());

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("ductuantran2410@gmail.com");
        message.setTo(user.getUsername()); 
        message.setSubject("New Password"); 
        message.setText("This is your new password: " + generatedPassword);
        emailSender.send(message);

        return new ResponseEntity<MessageResponse> (new MessageResponse("New Password Sent"), HttpStatus.OK);
    }
}
