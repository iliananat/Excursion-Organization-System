package com.example.demo.user;

import com.example.demo.config.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private String extractTokenFromHeader(String authorizationHeader) {
        // Check if the Authorization header is not null and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix
            return authorizationHeader.substring(7);
        }

        // If the header is not in the expected format, handle it accordingly
        throw new IllegalArgumentException("Invalid or missing Authorization header");
    }

    public User getUser(String afm) {
        return userRepository.findById(afm).orElse(null);
    }

    public User getUserFromToken(String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        Claims claims = jwtService.verifyJwtToken(token);
        // Extract information from claims
        String afm = claims.getSubject();
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