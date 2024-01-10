package com.example.demo.hello;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trip {
	@Id
	private String id;
	private TravelAgency travelAgency;
	private LocalDate startDate;
	private LocalDate endDate;
	private String depLocation;
	private String destLocation;
	private String schedule;
	private String maxNumOfParticipants;
    private int bookedSeats = 0;
    private int availableSeats;

	public Trip(){}
	
	public Trip(TravelAgency travelAgency, LocalDate startDate, LocalDate endDate, String depLocation,
			String destLocation, String schedule, String maxNumOfParticipants) {
		this.travelAgency = travelAgency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.depLocation = depLocation;
		this.destLocation = destLocation;
		this.schedule = schedule;
		this.maxNumOfParticipants = maxNumOfParticipants;
	}
	
	public String getID() {
		return id;
	}

	public TravelAgency getTravelAgency() {
		return travelAgency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getDepLocation() {
		return depLocation;
	}

	public String getDestLocation() {
		return destLocation;
	}
	
	public int getAvailableSeats() {
		availableSeats = Integer.getInteger(this.maxNumOfParticipants) - this.bookedSeats;
		return availableSeats;
	}
	
	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public String getMaxNumOfParticipants() {
		return maxNumOfParticipants;
	}

	public Trip getTrip() {
		return this;
	}

	
}
