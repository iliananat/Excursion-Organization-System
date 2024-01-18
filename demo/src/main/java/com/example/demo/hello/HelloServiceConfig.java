package com.example.demo.hello;

import com.example.demo.user.UserService;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class HelloServiceConfig implements CommandLineRunner {
    @Autowired
    private HelloService helloService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Citizen citizen = new Citizen("182710913", "citizen1", "Maria", "Pantazi", "maria.pa@example.com", passwordEncoder);
        Citizen citizen2 = new Citizen("177592412", "citizen2", "Iliana", "Natsiou", "ili.na@example.com", passwordEncoder);
        Citizen citizen3 = new Citizen("252960920", "citizen3", "Dimitris", "Kalaitzidis", "kalai@example.com", passwordEncoder);
        Citizen citizen4 = new Citizen("131704365", "citizen4", "Giorgos", "Xatziparaskeuas", "xatzi@example.com", passwordEncoder);
        TravelAgency travelAgency = new TravelAgency("987654322", "travel1", "Adventure Tours", "Georgia Smith", passwordEncoder);
        TravelAgency travelAgency2 = new TravelAgency("917843547", "travel2", "Adventure Trips", "Dimitris Jones", passwordEncoder);
        TravelAgency travelAgency3 = new TravelAgency("890923412", "travel3", "WeTravel", "Kostas Papadopoulos", passwordEncoder);
        userService.registerUser(citizen);
        userService.registerUser(citizen2);
        userService.registerUser(citizen3);
        userService.registerUser(citizen4);
        userService.registerUser(travelAgency);
        userService.registerUser(travelAgency2);
        userService.registerUser(travelAgency3);

        Trip trip = new Trip(travelAgency, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Athens", "Crete", "http://localhost/Tours/Schedule1.html", 53);
        Trip trip2 = new Trip(travelAgency2, LocalDate.of(2024, 01, 21), LocalDate.of(2024, 01, 25), "Athens", "Peloponnese", "http://localhost/Tours/Schedule2.html", 20);
        Trip trip3 = new Trip(travelAgency3, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Thessaloniki", "Athens", "http://localhost/Tours/Schedule3.html", 35);

        helloService.addTrip(trip);
        helloService.addTrip(trip2);
        helloService.addTrip(trip3);

        helloService.bookTrip(trip.getID(), citizen.getAfm(), 5);
        helloService.bookTrip(trip.getID(), citizen2.getAfm(), 5);
        helloService.bookTrip(trip2.getID(), citizen.getAfm(), 6);
        helloService.bookTrip(trip2.getID(), citizen2.getAfm(), 13);
        helloService.bookTrip(trip2.getID(), citizen3.getAfm(), 5);
        helloService.bookTrip(trip2.getID(), citizen3.getAfm(), 1);
        helloService.bookTrip(trip3.getID(), citizen4.getAfm(), 5);
    }
}
