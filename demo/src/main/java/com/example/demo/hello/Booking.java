package com.example.demo.hello;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "citizen_booked_afm")
	private Citizen citizenBooked;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "trip_booked_id")
	private Trip tripBooked;
	
	public Booking(){}
	
	public Booking(Trip tripBooked, Citizen citizenBooked) {
		this.tripBooked = tripBooked;
		this.citizenBooked = citizenBooked;
	}

	public Trip getTrip() {
		return tripBooked;
	}
	
	public Citizen getBookedCitizen() {
		return citizenBooked;
	}
	
	
}
