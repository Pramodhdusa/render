package com.example.jwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registration
    public User register(User user) {
        return userRepository.save(user);
    }

    // Login validation
    public Optional<User> login(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
}

}