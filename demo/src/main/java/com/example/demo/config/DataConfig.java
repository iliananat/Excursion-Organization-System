package com.example.demo.config;

import com.example.demo.booking.BookingService;
import com.example.demo.trip.TripService;
import com.example.demo.user.UserService;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;

import org.springframework.beans.factory.annotation.*;

@Configuration
public class DataConfig implements CommandLineRunner {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        Citizen citizen = new Citizen("801962655", "Citizen1!", "Maria", "Pantazi", "maria.pa@example.com");
//        Citizen citizen2 = new Citizen("998934837", "Citizen2!", "Iliana", "Natsiou", "ili.na@example.com");
//        Citizen citizen3 = new Citizen("999940368", "Citizen3!", "Dimitris", "Kalaitzidis", "kalai@example.com");
//        Citizen citizen4 = new Citizen("998902920", "Citizen4!", "Giorgos", "Xatziparaskeuas", "xatzi@example.com");
//        TravelAgency travelAgency = new TravelAgency("800449340", "Travel1!", "Adventure Tours", "Georgia Smith");
//        TravelAgency travelAgency2 = new TravelAgency("800491751", "Travel2!", "Adventure Trips", "Dimitris Jones");
//        TravelAgency travelAgency3 = new TravelAgency("801508680", "Travel3!", "WeTravel", "Kostas Papadopoulos");
//        userService.registerUser(citizen);
//        userService.registerUser(citizen2);
//        userService.registerUser(citizen3);
//        userService.registerUser(citizen4);
//        userService.registerUser(travelAgency);
//        userService.registerUser(travelAgency2);
//        userService.registerUser(travelAgency3);
//
//        Trip trip = new Trip(travelAgency, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Athens", "Crete", "http://localhost/Tours/Schedule1.html", 53);
//        Trip trip2 = new Trip(travelAgency2, LocalDate.of(2024, 01, 21), LocalDate.of(2024, 01, 25), "Athens", "Peloponnese", "http://localhost/Tours/Schedule2.html", 20);
//        Trip trip3 = new Trip(travelAgency3, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Thessaloniki", "Athens", "http://localhost/Tours/Schedule3.html", 35);
//        tripService.addTrip(trip);
//        tripService.addTrip(trip2);
//        tripService.addTrip(trip3);
//
//        bookingService.bookTrip(trip.getID(), citizen.getAfm(), 5);
//        bookingService.bookTrip(trip.getID(), citizen2.getAfm(), 5);
//        bookingService.bookTrip(trip2.getID(), citizen.getAfm(), 6);
//        bookingService.bookTrip(trip2.getID(), citizen2.getAfm(), 13);
//        bookingService.bookTrip(trip2.getID(), citizen3.getAfm(), 5);
//        bookingService.bookTrip(trip2.getID(), citizen3.getAfm(), 1);
//        bookingService.bookTrip(trip3.getID(), citizen4.getAfm(), 5);
    }
}
