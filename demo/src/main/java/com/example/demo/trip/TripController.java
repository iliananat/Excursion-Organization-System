package com.example.demo.trip;

import com.example.demo.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping(path = "/trips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    // Get citizen's booked trips
    @GetMapping(path = "/{afm}/mybookedTrips")
    public List<Booking> myBookedTrips(@PathVariable String afm) {
        return tripService.getMyBookings(afm);
    }

    // Get travel agency trips
    @GetMapping(path = "/{afm}/mytravelAgencyTrips")
    public List<Trip> travelAgencyTrips(@PathVariable String afm) {
        return tripService.getTravelAgencyTrips(afm);
    }

    @PostMapping(path = "/{afm}/addTrip")
    public void addTrip(@PathVariable String afm, @RequestBody Trip t) {
        tripService.addTrip(t);
    }

    // Searching trips
    @GetMapping(path = "/{afm}/search")
    public List<Trip> searchTrips(@PathVariable String afm,
                                  @RequestParam(required = false) String depLoc,
                                  @RequestParam(required = false) String destLoc,
                                  @RequestParam(required = false) String startdt,
                                  @RequestParam(required = false) String enddt) {
        return tripService.searchTrips(depLoc, destLoc, startdt, enddt);
    }
}
