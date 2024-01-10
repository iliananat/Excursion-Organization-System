package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class HelloService {

	@Autowired
	private TripRepository tripRepository;
	private UserRepository userRepository;
//	@Autowired
//	private ProfessorRepository profRepository;
//	
//	public void addProfessor(Professor p) throws Exception {
//		Optional<Professor> byId = profRepository.findById(p.getName());
//		if(!byId.isPresent())
//			profRepository.save(p);
//	}

	public void addTrip(Trip t) throws Exception {
		Optional<Trip> byId = tripRepository.findById(t.getTravelAgency().getAfm());
		if(!byId.isPresent())
			tripRepository.save(t);
	}

	public List<Trip> getAllTrips() throws Exception {
		return tripRepository.findAll();
	}
	
	// Register User
    public void registerUser(User user) {
        if (user.isValidRegistration()) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid registration for user");
        }
    }
	
	// Book Trip
	public boolean bookTrip(Trip tripBooked, int numOfPeopleBooked) {
		// Get the selected trip
		Trip trip = tripRepository.findById(tripBooked.getTravelAgency().getAfm()).orElse(null);
		// Check if there are available seat
		if (trip != null && trip.getAvailableSeats() > 0) {
			// Update booked seats and save
			trip.setBookedSeats(trip.getBookedSeats() + numOfPeopleBooked);
			tripRepository.save(trip);
			return true; // Booking successful
		} else {
			return false; // No available seats or trip not found
		}
	}

}