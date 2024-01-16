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
		
		/*Citizen citizen = new Citizen("123456781", "citizen1", "Maria", "Pantazi", "john.doe@example.com");
		TravelAgency travelAgency = new TravelAgency("987654322", "travel1", "Adventure Tours", "Georgia Smith");
		Citizen citizen2 = new Citizen("111111111", "citizen2", "Iliana", "Natsiou", "ili.na@example.com");
		TravelAgency travelAgency2 = new TravelAgency("222222222", "travel2", "Adventure Trips", "Dimitris Smith");
		Citizen citizen3 = new Citizen("522222222", "citizen3", "Dimitris", "Kalaitzidis", "kalai@example.com");
		Citizen citizen4 = new Citizen("121212121", "citizen4", "Giorgos", "Xatziparaskeuas", "xatzi@example.com");
		hs.registerUser(citizen);
		hs.registerUser(travelAgency);
		hs.registerUser(travelAgency2);	
		hs.registerUser(citizen2);
		hs.registerUser(citizen3);
		hs.registerUser(citizen4);
		
		LocalDate startDate = LocalDate.now();  
		LocalDate endDate = startDate.plusDays(7);  
		Trip trip = new Trip(travelAgency, startDate, endDate, "DepLocation", "DestLocation", "Schedule", 53);
		Trip trip2 = new Trip(travelAgency2, startDate, endDate, "Departure", "Destination", "Schedule", 20);
		hs.addTrip(trip);
		hs.addTrip(trip2);
			
		hs.bookTrip(trip.getID(), citizen.getAfm(), 5);
		hs.bookTrip(trip.getID(), citizen2.getAfm(), 5);
		hs.bookTrip(trip2.getID(), citizen.getAfm(), 6);
		hs.bookTrip(trip2.getID(), citizen2.getAfm(), 13);
		hs.bookTrip(trip2.getID(), citizen3.getAfm(), 5); // >20
		hs.bookTrip(trip2.getID(), citizen3.getAfm(), 1);*/

	}

}
