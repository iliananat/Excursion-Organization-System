package com.example.demo.hello;

import java.util.List;

import com.example.demo.booking.Booking;
import com.example.demo.trip.Trip;
import com.example.demo.user.Citizen;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

// Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {
	@Autowired
	private HelloService hs;
	
	@GetMapping(path="/trips")
	public List<Trip> getAllTrips() throws Exception {
		return hs.getAllTrips();
	}
	
	// Get citizen's booked trips
	@GetMapping(path="/{afm}/mybookedTrips")
	public List<Booking> myBookedTrips(@PathVariable String afm) throws Exception {
		User loggedInUser = hs.getLoggedInUser(afm);
		if(loggedInUser instanceof Citizen) {
			return hs.getMyBookings(afm);
		}
		return null;

	}
	
	// Get travel agency trips
	@GetMapping(path="/{afm}/mytravelAgencyTrips")
	public List<Trip> travelAgencyTrips(@PathVariable String afm) throws Exception {
		User loggedInUser = hs.getLoggedInUser(afm);
		if(loggedInUser instanceof TravelAgency) {
			return hs.getTravelAgencyTrips(afm);
		}
		return null;
	}
	
	@PostMapping(path="/{afm}/addTrip")
	public void addTrip(@PathVariable String afm, @RequestBody Trip t) throws Exception {
		if(hs.isLoggedIn(afm)) {
			hs.addTrip(t);
		}
	}
    
    // Logout User
    @PostMapping(path="/{afm}/logout")
    public String logout(@PathVariable String afm) throws Exception{
    	if (hs.isLoggedIn(afm)) {
    		hs.logout(afm);
    		return "Succesfully logout";
    	} else {
    		return "Did not log out";
    	}
    }
    
    // Booking a trip
    @PostMapping(path="/{afm}/booking")
    public void bookTrip(@PathVariable String afm, @RequestParam Long tripID,
    		 @RequestParam int numOfPeopleBooked) throws Exception{
    	if (hs.isLoggedIn(afm) && hs.getLoggedInUser(afm) instanceof Citizen) {
	    	boolean bookingResult = hs.bookTrip(tripID, afm, numOfPeopleBooked);
	        if (bookingResult) {
	            System.out.println("Trip booked successfully");
	        } else {
	        	System.out.println("Booking failed. No available seats or trip not found.");
	        }
    	}
    }
    
    // Searching trips
    @GetMapping(path="/{afm}/search")
    public List<Trip> searchTrips(
    		@PathVariable String afm,
            @RequestParam(required=false) String depLoc,
            @RequestParam(required=false) String destLoc,
            @RequestParam(required=false) String startdt,
            @RequestParam(required=false) String enddt
    ) throws Exception{
    	if (hs.isLoggedIn(afm)) {
	        
	        List<Trip> searchedTrips = hs.searchTrips(depLoc, destLoc, startdt, enddt);
	        return searchedTrips;
    	} else {
    		return null;
    	}
    }

    
	
}
