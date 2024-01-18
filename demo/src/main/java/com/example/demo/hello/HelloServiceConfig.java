package com.example.demo.hello;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;

import antlr.collections.List;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.*;

@Configuration
public class HelloServiceConfig implements CommandLineRunner {
	
	@Autowired
	private HelloService hs;
	
	@Override
	public void run(String... args) throws Exception {
		
//		Citizen citizen = new Citizen("182710913", "citizen1", "Maria", "Pantazi", "maria.pa@example.com");
//		Citizen citizen2 = new Citizen("177592412", "citizen2", "Iliana", "Natsiou", "ili.na@example.com");
//		Citizen citizen3 = new Citizen("252960920", "citizen3", "Dimitris", "Kalaitzidis", "kalai@example.com");
//		Citizen citizen4 = new Citizen("131704365", "citizen4", "Giorgos", "Xatziparaskeuas", "xatzi@example.com");
//		TravelAgency travelAgency = new TravelAgency("987654322", "travel1", "Adventure Tours", "Georgia Smith");
//		TravelAgency travelAgency2 = new TravelAgency("917843547", "travel2", "Adventure Trips", "Dimitris Jones");
//		TravelAgency travelAgency3 = new TravelAgency("890923412", "travel3", "WeTravel", "Kostas Papadopoulos");
//		hs.registerUser(citizen);
//		hs.registerUser(citizen2);
//		hs.registerUser(citizen3);
//		hs.registerUser(citizen4);
//		hs.registerUser(travelAgency);
//		hs.registerUser(travelAgency2);
//		hs.registerUser(travelAgency3);
//
//		Trip trip = new Trip(travelAgency, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Athens", "Crete", "http://localhost/Tours/Schedule1.html", 53);
//		Trip trip2 = new Trip(travelAgency2, LocalDate.of(2024, 01, 21), LocalDate.of(2024, 01, 25), "Athens", "Peloponnese", "http://localhost/Tours/Schedule2.html", 20);
//		Trip trip3 = new Trip(travelAgency3, LocalDate.of(2024, 01, 19), LocalDate.of(2024, 01, 22), "Thessaloniki", "Athens", "http://localhost/Tours/Schedule3.html" ,35);
//
//		hs.addTrip(trip);
//		hs.addTrip(trip2);
//		hs.addTrip(trip3);
//
//		hs.bookTrip(trip.getID(), citizen.getAfm(), 5);
//		hs.bookTrip(trip.getID(), citizen2.getAfm(), 5);
//		hs.bookTrip(trip2.getID(), citizen.getAfm(), 6);
//		hs.bookTrip(trip2.getID(), citizen2.getAfm(), 13);
//		hs.bookTrip(trip2.getID(), citizen3.getAfm(), 5); // >20
//		hs.bookTrip(trip2.getID(), citizen3.getAfm(), 1);
//		hs.bookTrip(trip3.getID(), citizen4.getAfm(), 5);

	}

}
