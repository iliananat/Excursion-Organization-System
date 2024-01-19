package com.example.demo.trip;

import com.example.demo.config.InfoResponse;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @GetMapping(path = "")
    public ResponseEntity<?> getAllTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null) {
            if (user.getUserType().equals("citizen")) {
                return ResponseEntity.ok(tripService.getCitizenTrips());
            } else {
                return ResponseEntity.ok(tripService.getTravelAgencyTrips(user.getAfm()));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                         @RequestParam(required = false) String departureLocation,
                                         @RequestParam(required = false) String destinationLocation,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null) {
            return ResponseEntity.ok(tripService.searchTrips(departureLocation, destinationLocation, endDate, startDate));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @PostMapping(path = "")
    public ResponseEntity<?> insertTrip(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody Trip trip) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("travel_agency")) {
            trip.setTravelAgency((TravelAgency) user);
            tripService.insertTrip(trip);
            return ResponseEntity.ok(new InfoResponse("Ολοκληρώθηκε με επιτυχία!"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }
}
