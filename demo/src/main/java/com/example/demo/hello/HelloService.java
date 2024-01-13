package com.example.demo.hello;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class HelloService {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
    
    // Map to store logged-in users with user ID (AFM) as the key and password as the value
    private Map<String, User> loggedInUsers = new ConcurrentHashMap<>();

	public void addTrip(Trip t) throws Exception {
		Optional<Trip> byId = tripRepository.findById(t.getTravelAgency().getAfm());
		if(!byId.isPresent())
			tripRepository.save(t);
	}

	public List<Trip> getAllTrips() throws Exception {
		return tripRepository.findAll();
	}
	
	public List<Booking> getMyBookings(Citizen c) throws Exception {
		List<Booking> bookings = bookingRepository.findAll();
		List<Booking> myBookings = new ArrayList<>();
		for (Booking booking : bookings) {
			if (booking.getBookedCitizen().equals(c)) {
				myBookings.add(booking);
			}
		}
		return myBookings;
	}
	
	public List<Trip> getTravelAgencyTrips(TravelAgency t) throws Exception {
		List<Trip> allTrips = getAllTrips();
		List<Trip> travelAgencyTrips = new ArrayList<>();
		for (Trip trip: allTrips) {
			if (trip.getTravelAgency().equals(t)) {
				travelAgencyTrips.add(trip);
			}
		}
		return travelAgencyTrips;
	}
	
	// Register User
    public void registerUser(User user) {
        if (user.isValidRegistration()) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid registration for user");
        }
    }
    
    // Login User
    public User login(String afm, String password) throws Exception{
    	User loginUser = userRepository.findById(afm).orElse(null);
    	if(loginUser != null && loginUser.getPassword().equals(password)) {
    		loggedInUsers.put(loginUser.getAfm(), loginUser);
    		return loginUser;
    	}
    	return null;
    }
    
    // Logout User
    public void logout(String afm) {
        loggedInUsers.remove(afm);
    }
    
    // Get Logged in User
    public User getLoggedInUser(String afm) throws Exception{
		if (loggedInUsers.containsKey(afm)) {
			User loggedInUser = loggedInUsers.get(afm);
			return loggedInUser;
		} else {
			return null;
		}

    }
    
    // Check if User is Logged In
    public boolean isLoggedIn(String afm) {
        return loggedInUsers.containsKey(afm);
    }
	
	// Book Trip
	public boolean bookTrip(Booking booking , int numOfPeopleBooked) {
		
	    // Validate inputs
	    if (booking == null || numOfPeopleBooked <= 0) {
	        return false; // Invalid inputs
	    }
	    
		// Get the selected trip
		Trip trip = tripRepository.findById(booking.getTrip().getID()).orElse(null);
		// Check if there are available seat
		if (trip != null && trip.getAvailableSeats() > 0) {
			// Update booked seats and save
			trip.setBookedSeats(trip.getBookedSeats() + numOfPeopleBooked);
			tripRepository.save(trip);
			// Save booking
			bookingRepository.save(booking);
			return true; // Booking successful
		} else {
			return false; // No available seats or trip not found
		}
	}
	
    public List<Trip> searchTrips(String depLoc, String destLoc, LocalDate startdt, LocalDate enddt){
    	List<Trip> trips = tripRepository.findAll();
		List<Trip> temp = trips;
		
		if(depLoc!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getDepLocation().equals(depLoc)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}

		if(destLoc!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getDestLocation().equals(destLoc)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		
		if(startdt!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getStartDate().equals(startdt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		if(enddt!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getEndDate().equals(enddt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		return temp;
    }

}