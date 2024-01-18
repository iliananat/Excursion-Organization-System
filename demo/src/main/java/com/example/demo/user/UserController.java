package com.example.demo.user;

import com.example.demo.config.InfoResponse;
import com.example.demo.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/me")
    public User getMe(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return userService.getUserFromToken(authorizationHeader);
    }

    @GetMapping(path = "/{afm}")
    public ResponseEntity<?> getUser(@PathVariable String afm) {
        User user = userService.getUser(afm);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InfoResponse("Λάθος Όνομα Χρήστη"));
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String afm = loginRequest.get("afm");
        String password = loginRequest.get("password");
        User user = userService.getUser(afm);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InfoResponse("Λάθος Όνομα Χρήστη ή Κωδικός Χρήστη"));
        } else if (user.getLoginAttempts() > 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
        } else {
            if (user.isPasswordCorrect(password, passwordEncoder)) {
                return ResponseEntity.ok(new InfoResponse(jwtService.generateJwtToken(user.getAfm())));
            } else {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                userService.updateUser(user);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InfoResponse("Λάθος Όνομα Χρήστη ή Κωδικός Χρήστη. Απομένουν " + (3 - user.getLoginAttempts()) + " προσπάθειες."));
            }
        }
    }

    @PostMapping(path = "/register/citizen")
    public void registerCitizen(@RequestBody Citizen citizen) {
        userService.registerUser(citizen);
    }

    @PostMapping(path = "/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency travelAgency) {
        userService.registerUser(travelAgency);
    }
}
