package com.example.demo.hello;

import java.time.LocalDate;
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
	
	@GetMapping(path="/trips")
	public List<Trip> getAllTrips() throws Exception {
		return hs.getAllTrips();
	}
	
	@PostMapping(path="/addTrip")
	public void addTrip(@RequestBody Trip t) throws Exception {
		hs.addTrip(t);
	}
	
    @PostMapping(path="/register/citizen")
    public void registerCitizen(@RequestBody Citizen c) throws Exception{
        Citizen citizen = new Citizen(c.getAfm(), c.getPassword(), c.getFirstName(), c.getLastName(), c.getEmail());
        hs.registerUser(citizen);
    }
 

    @PostMapping(path="/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency t) {
        TravelAgency travelAgency = new TravelAgency(t.getAfm(), t.getPassword(), t.getName(), t.getOwner());
        hs.registerUser(travelAgency);
    }
    
    // Log in user
    @PostMapping(path="/login")
    public User login(@RequestBody Map<String, String> loginRequest) throws Exception{
        String afm = loginRequest.get("afm");
        String password = loginRequest.get("password");
        User loggedUser = hs.login(afm, password);
        
        if (loggedUser != null) {
            System.out.println("Logged in successfully!");
        } else {
            System.out.println("Wrong AFM or Password!");
        }
        
        return loggedUser;
        
    }
    
    // Booking a trip
    @PostMapping(path="/booking")
    public void bookTrip(@RequestBody Booking b, int numOfPeopleBooked) throws Exception{
    	Booking booking = new Booking(b.getTrip(), b.getBookedCitizen());
    	boolean bookingResult = hs.bookTrip(booking, numOfPeopleBooked);
        if (bookingResult) {
            System.out.println("Trip booked successfully");
        } else {
        	System.out.println("Booking failed. No available seats or trip not found.");
        }
    }
    
    // Searching trips
    @GetMapping(path="/search")
    public List<Trip> searchTrips(
            @RequestParam String depLoc,
            @RequestParam String destLoc,
            @RequestParam String startdt,
            @RequestParam String enddt
    ) throws Exception{
        LocalDate startDate = LocalDate.parse(startdt);
        LocalDate endDate = LocalDate.parse(enddt);
        
        List<Trip> searchedTrips = hs.searchTrips(depLoc, destLoc, startDate, endDate);
        return searchedTrips;
    }

    
	
}
