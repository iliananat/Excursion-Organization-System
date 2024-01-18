package com.example.demo.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping(path="/{afm}")
    public void bookTrip(@PathVariable String afm, @RequestParam Long tripID, @RequestParam int numOfPeopleBooked) {
        boolean bookingResult = bookingService.bookTrip(tripID, afm, numOfPeopleBooked);
        if (bookingResult) {
            System.out.println("Trip booked successfully");
        } else {
            System.out.println("Booking failed. No available seats or trip not found.");
        }
    }
}
