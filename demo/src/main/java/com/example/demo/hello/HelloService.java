package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class HelloService {

	@Autowired
	private TripRepository tripRepository;
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

}
