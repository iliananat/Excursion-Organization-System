package com.example.demo.booking;

import com.example.demo.hello.Citizen;
import com.example.demo.trip.Trip;

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
	
	private int numOfPeopleBooked;
	
	public Booking(){}
	
	public Booking(Trip tripBooked, Citizen citizenBooked, int numOfPeopleBooked) {
		this.tripBooked = tripBooked;
		this.citizenBooked = citizenBooked;
		this.numOfPeopleBooked = numOfPeopleBooked;
	}

	public Trip getTrip() {
		return tripBooked;
	}
	
	public Citizen getBookedCitizen() {
		return citizenBooked;
	}
	
	public int getNumOfPeopleBooked() {
		return numOfPeopleBooked;
	}
	
	
}
