package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(String afm, String password) throws Exception {
        User loginUser = userRepository.findById(afm).orElse(null);
        if (loginUser != null && loginUser.getPassword().equals(password)) {
            return loginUser;
        }
        return null;
    }
}