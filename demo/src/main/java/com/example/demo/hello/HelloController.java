package com.example.demo.hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

// Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

	@Autowired
	private HelloService hs;
	
	@GetMapping(path="/")
	public String homePage() {
		return "Home Page";
	}
	
	@GetMapping(path="/{afm}")
	public String userPage(@PathVariable String afm) {
		if (hs.isLoggedIn(afm)) {
			return "userPage"; 
		}
		return null;
	}
	
	@GetMapping(path="/trips")
	public List<Trip> getAllTrips() throws Exception {
		return hs.getAllTrips();
	}
	
	@GetMapping(path="/{afm}/mybookedTrips")
	public List<Booking> myBookedTrips(@PathVariable String afm) throws Exception {
		User loggedInUser = hs.getLoggedInUser(afm);
		if(loggedInUser instanceof Citizen) {
			return hs.getMyBookings(afm);
		}
		return null;

	}
	
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
	
    @PostMapping(path="/register/citizen")
    public void registerCitizen(@RequestBody Citizen c) {
        Citizen citizen = new Citizen(c.getAfm(), c.getPassword(), c.getFirstName(), c.getLastName(), c.getEmail());
        hs.registerUser(citizen);
    }
 

    @PostMapping(path="/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency t) {
        TravelAgency travelAgency = new TravelAgency(t.getAfm(), t.getPassword(), t.getName(), t.getOwner());
        hs.registerUser(travelAgency);
    }
    
    // Log in user and redirect
    @PostMapping(path="/login")
    public User login(@RequestBody Map<String, String> loginRequest) throws Exception{
        String afm = loginRequest.get("afm");
        String password = loginRequest.get("password");
        User loggedUser = hs.login(afm, password);
        
        if (loggedUser != null) {
            System.out.println("Logged in successfully!");
            return loggedUser;
        } else {
            System.out.println("Wrong AFM or Password!");
            return null;
        }
        
    }
    
    // Logout User and redirect
    @PostMapping(path="/{afm}/logout")
    public String logout(@PathVariable String afm) throws Exception{
    	if (hs.isLoggedIn(afm)) {
    		hs.logout(afm);
    		return "redirect:/";
    	} else {
    		return null;
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
