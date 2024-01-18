package com.example.demo.user;

import com.example.demo.hello.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{afm}")
    public User getUser(@PathVariable String afm) {
        return userService.getUser(afm);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String afm = loginRequest.get("afm");
        String password = loginRequest.get("password");
        User user = userService.getUser(afm);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        } else if (user.getLoginAttempts() > 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Access forbidden. Contact administrator"));
        } else {
            if (user.isPasswordCorrect(password, passwordEncoder)) {
                return ResponseEntity.ok(user);
            } else {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                userService.updateUser(user);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Wrong credentials! " + (3 - user.getLoginAttempts()) + " attempts remaining."));
            }
        }
    }

    @PostMapping(path = "/register/citizen")
    public void registerCitizen(@RequestBody Citizen c) {
        Citizen citizen = new Citizen(c.getAfm(), c.getPassword(), c.getFirstName(), c.getLastName(), c.getEmail());
        userService.registerUser(citizen);
    }

    @PostMapping(path = "/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency t) {
        TravelAgency travelAgency = new TravelAgency(t.getAfm(), t.getPassword(), t.getName(), t.getOwner());
        userService.registerUser(travelAgency);
    }
}
