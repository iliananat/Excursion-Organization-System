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

    public User login(String afm, String password) {
        User user = userRepository.findById(afm).orElse(null);
        if (user != null && user.isPasswordCorrect(password, passwordEncoder)) {
            return user;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public void registerUser(User user) {
        try {
            if (user.isValidRegistration() && !userRepository.existsById(user.getAfm())) {
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Invalid registration for user");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error during user registration: " + e.getMessage());
        }
    }
}