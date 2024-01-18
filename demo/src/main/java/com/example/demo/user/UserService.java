package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User getUser(String afm) {
        return userRepository.findById(afm).orElse(null);
    }

    public void registerUser(User user) {
        try {
            if (user.isValidRegistration() && !userRepository.existsById(user.getAfm())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid registration for user");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}