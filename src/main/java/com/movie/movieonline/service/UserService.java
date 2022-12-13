package com.movie.movieonline.service;

import java.util.List;

import com.movie.movieonline.domain.User;
import com.movie.movieonline.service.impl.UserDetailsImpl;

public interface UserService {
    void deleteUser(Long userId);
    User updateUser(User user);
    public User loadUserByUsername(String username);
    void changePassword(String newPass, Long id);
    List<UserDetailsImpl> getAllUserInfo();
}
