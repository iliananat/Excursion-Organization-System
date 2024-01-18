package com.example.demo.booking;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping(path="/{afm}")
    public void bookTrip(@PathVariable String afm, @RequestParam Long tripID, @RequestParam int numOfPeopleBooked) {
        boolean bookingResult = bookingService.bookTrip(tripID, afm, numOfPeopleBooked);
        if (bookingResult) {
            System.out.println("Trip booked successfully");
        } else {
            System.out.println("Booking failed. No available seats or trip not found.");
        }
    }

    // Get citizen's booked trips
    @GetMapping(path = "/bookedTrips")
    public List<Booking> myBookedTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        User user = userService.getUserFromToken(authorizationHeader);
        return bookingService.getMyBookings(user.getAfm());
    }
}
