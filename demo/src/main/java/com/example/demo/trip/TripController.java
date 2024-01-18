package com.example.demo.trip;

import com.example.demo.config.InfoResponse;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @GetMapping(path = "/trips")
    public ResponseEntity<?> getAllTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("citizen")) {
            return ResponseEntity.ok(tripService.getAllTrips());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @GetMapping(path = "/travelAgencyTrips")
    public ResponseEntity<?> travelAgencyTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("travel_agency")) {
            return ResponseEntity.ok(tripService.getTravelAgencyTrips(user.getAfm()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @PostMapping(path = "/addTrip")
    public ResponseEntity<?> addTrip(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody Trip trip) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null && user.getUserType().equals("travel_agency")) {
            trip.setTravelAgency((TravelAgency) user);
            tripService.addTrip(trip);
            return ResponseEntity.ok(new InfoResponse("Ολοκληρώθηκε με επιτυχία!"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchTrips(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                  @RequestParam(required = false) String depLoc,
                                  @RequestParam(required = false) String destLoc,
                                  @RequestParam(required = false) String startdt,
                                  @RequestParam(required = false) String enddt) {
        User user = userService.getUserFromToken(authorizationHeader);
        if (user != null) {
            return ResponseEntity.ok(tripService.searchTrips(depLoc, destLoc, startdt, enddt));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Απαγορεύτηκε η είσοδος"));
    }
}
