package com.example.demo.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private int attempts = 0;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{afm}")
    public User getUser(@PathVariable String afm) {
        return userService.getUser(afm);
    }

    @PostMapping(path = "/login")
    public User login(@RequestBody Map<String, String> loginRequest) throws Exception {
        String afm = loginRequest.get("afm");
        String password = loginRequest.get("password");
        User loggedUser = userService.login(afm, password);

        if (loggedUser != null) {
            attempts = 0;
            System.out.println("Logged in successfully!");
            return loggedUser;
        } else {
            attempts++;
            if (attempts < 3) {
                System.out.print("Wrong AFM or Password! " + (3 - attempts) + " attempts remaining.");
            } else {
                attempts = 0;
                System.out.println("Access forbidden. Wait 10 seconds and try again");
                Thread.sleep(10000);
                System.out.println("3 attempts remaining");
            }
            return null;
        }
    }

    @PostMapping(path = "/register/citizen")
    public void registerCitizen(@RequestBody Citizen c) {
        Citizen citizen = new Citizen(c.getAfm(), c.getPassword(), c.getFirstName(), c.getLastName(), c.getEmail(), passwordEncoder);
        userService.registerUser(citizen);
    }

    @PostMapping(path = "/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency t) {
        TravelAgency travelAgency = new TravelAgency(t.getAfm(), t.getPassword(), t.getName(), t.getOwner(), passwordEncoder);
        userService.registerUser(travelAgency);
    }
}
