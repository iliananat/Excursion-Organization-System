package com.example.demo.hello;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

// Since this is the controller, it is the place where we add our end-points
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

	@Autowired
	private HelloService hs;
	
	@PostMapping(path="/addTrip")
	public void addTrip(@RequestBody Trip t) throws Exception {
		hs.addTrip(t);
	}
	
    @PostMapping("/register/citizen")
    public void registerCitizen(@RequestBody Citizen c) throws Exception{
        Citizen citizen = new Citizen(c.getAfm(), c.getPassword(), c.getFirstName(), c.getLastName(), c.getEmail());
        hs.registerUser(citizen);
    }
 

    @PostMapping("/register/travel-agency")
    public void registerTravelAgency(@RequestBody TravelAgency t) {
        TravelAgency travelAgency = new TravelAgency(t.getAfm(), t.getPassword(), t.getName(), t.getOwner());
        hs.registerUser(travelAgency);
    }
	
//	@GetMapping(path="/students")
//	public List<Student> getAllStudent()  throws Exception{
//		return hs.getAllStudents();
//	} 
//	
//	@PostMapping(path="/addStudent")
//	public void addStudent(@RequestBody Student st) throws Exception {
//		hs.addStudent(st);
//	}
}
