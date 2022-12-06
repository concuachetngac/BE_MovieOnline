package com.movie.movieonline.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.movieonline.domain.User;
import com.movie.movieonline.exception.ResourceNotFoundException;
import com.movie.movieonline.repository.UserRepository;
import com.movie.movieonline.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
	PasswordEncoder encoder;

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> 
                                new ResourceNotFoundException("User", "Id", userId));
        userRepository.deleteById(userId);
        
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return user;
	}

    @Override
    public void changePassword(String newPass, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> 
                                new ResourceNotFoundException("User", "Id", id));
        existingUser.setPassword(encoder.encode(newPass));
        userRepository.save(existingUser);
    }

}
