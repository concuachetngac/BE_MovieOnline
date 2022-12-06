package com.movie.movieonline.service;

import com.movie.movieonline.domain.User;

public interface UserService {
    void deleteUser(Long userId);
    User updateUser(User user);
    public User loadUserByUsername(String username);
    void changePassword(String newPass, Long id);
}
