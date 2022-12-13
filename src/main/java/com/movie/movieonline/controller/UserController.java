package com.movie.movieonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.User;
import com.movie.movieonline.service.UserService;
import com.movie.movieonline.service.impl.UserDetailsImpl;
import com.movie.movieonline.util.LoginRequest;
import com.movie.movieonline.util.MessageResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("detail")
    public ResponseEntity<User> getUserDetail(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("password/change")
    public ResponseEntity<?> changePassword(@RequestBody LoginRequest userPassword){

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        userService.changePassword(userPassword.getPassword(), user.getId());

        return new ResponseEntity<MessageResponse> (new MessageResponse("Password Changed"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<?> getAllUserDetail(){
        return new ResponseEntity<List<UserDetailsImpl>> (userService.getAllUserInfo(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<MessageResponse> (new MessageResponse("Delete User Successful"), HttpStatus.OK);
    }


}
