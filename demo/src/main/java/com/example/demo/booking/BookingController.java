package com.example.demo.booking;

import com.example.demo.config.InfoResponse;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping(path="")
    public ResponseEntity<?> insertBooking(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody BookingRequest bookingRequest) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("citizen")) {
            return bookingService.insertBooking(bookingRequest, user);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }
}
