package com.example.demo.hello;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.user.Citizen;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
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
		tripRepository.save(t);
	}

	public List<Trip> getAllTrips() throws Exception {
		return tripRepository.findAll();
	}
	
	public List<Booking> getMyBookings(String afm) throws Exception {
		Citizen citizen = (Citizen) userRepository.findById(afm).orElse(null);
		List<Booking> bookings = bookingRepository.findAll();
	    List<Booking> myBookings = new ArrayList<>();

	    for (Booking booking : bookings) {
	        if (booking.getBookedCitizen().equals(citizen)) {
	            myBookings.add(booking);
	        }
	    }
		return myBookings;
	}
	
	public List<Trip> getTravelAgencyTrips(String afm) throws Exception {
		TravelAgency travelAgency = (TravelAgency) userRepository.findById(afm).orElse(null);
		List<Trip> allTrips = tripRepository.findAll();
		List<Trip> travelAgencyTrips = new ArrayList<>();
		for (Trip trip: allTrips) {
			if (trip.getTravelAgency().getAfm().equals(travelAgency.getAfm())) {
				travelAgencyTrips.add(trip);
			}
		}
		return travelAgencyTrips;
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
	public boolean bookTrip(Long tripID, String citizenAFM , int numOfPeopleBooked) {
		
		Trip selectedTrip = tripRepository.findById(tripID).orElse(null);
		Citizen currCitizen = (Citizen) userRepository.findById(citizenAFM).orElse(null);
		
	    // Validate inputs
	    if (selectedTrip == null || currCitizen == null || numOfPeopleBooked < 0) {
	        return false; // Invalid inputs
	    }
	    
		selectedTrip.setBookedSeats(selectedTrip.getBookedSeats() + numOfPeopleBooked);

		// Check if there are available seat
		if (selectedTrip.getAvailableSeats() >= 0) {
				// Update booked seats of trip
				tripRepository.save(selectedTrip);
				// Save booking
				Booking booking = new Booking(selectedTrip, currCitizen, numOfPeopleBooked);
				bookingRepository.save(booking);
				return true; // Booking successful
		} else {selectedTrip.setBookedSeats(selectedTrip.getBookedSeats() - numOfPeopleBooked);
				return false; // No seats
		}
	}
	
	
    public List<Trip> searchTrips(String depLoc, String destLoc, String startDate, String endDate){
    	List<Trip> trips = tripRepository.findAll();
		List<Trip> temp = trips;
		
		if(startDate!=null) {
			LocalDate startdt = LocalDate.parse(startDate);
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getStartDate().equals(startdt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		if(endDate!=null) {
			LocalDate enddt = LocalDate.parse(endDate);
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getEndDate().equals(enddt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		
		if(depLoc!=null) {
			int locLength=depLoc.length();
			int index=0;
			while(index<temp.size()) {
				String comp="";
				for(int j=0;j<locLength;j++) {
					comp=comp+temp.get(index).getDepLocation().charAt(j);
				}
				if(!comp.toLowerCase().equals(depLoc.toLowerCase())) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}

		if(destLoc!=null) {
			int destLength=destLoc.length();
			int index=0;
			while(index<temp.size()) {
				String comp="";
				for(int j=0;j<destLength;j++) {
					comp=comp+temp.get(index).getDestLocation().charAt(j);
				}
				if(!comp.toLowerCase().equals(destLoc.toLowerCase())) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}

		return temp;
    }

}