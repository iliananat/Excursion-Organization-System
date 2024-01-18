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
    public ResponseEntity<?> bookTrip(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestParam Long tripID, @RequestParam int numOfPeopleBooked) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("citizen")) {
            boolean bookingResult = bookingService.bookTrip(tripID, user.getAfm(), numOfPeopleBooked);
            if (bookingResult) {
                return ResponseEntity.ok().body(new InfoResponse("Ολοκληρώθηκε με επιτυχία"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Δεν υπάρχουν διαθέσιμες θέσεις"));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @GetMapping(path = "/bookedTrips")
    public ResponseEntity<?> myBookedTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("citizen")) {
            return ResponseEntity.ok(bookingService.getMyBookings(user.getAfm()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }
}
