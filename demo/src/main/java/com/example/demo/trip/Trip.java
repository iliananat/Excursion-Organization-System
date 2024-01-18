package com.example.demo.trip;

import com.example.demo.user.TravelAgency;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "travel_agency_afm")
	private TravelAgency travelAgency;
	private LocalDate startDate;
	private LocalDate endDate;
	private String depLocation;
	private String destLocation;
	private String schedule;
	private int maxNumOfParticipants;
    private int bookedSeats = 0;

	public Trip(){}
	
	public Trip(TravelAgency travelAgency, LocalDate startDate, LocalDate endDate, String depLocation,
			String destLocation, String schedule, int maxNumOfParticipants) {
		this.travelAgency = travelAgency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.depLocation = depLocation;
		this.destLocation = destLocation;
		this.schedule = schedule;
		this.maxNumOfParticipants = maxNumOfParticipants;
	}
	
	public Long getID() {
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
		return maxNumOfParticipants - bookedSeats;
	}
	
	public int getBookedSeats() {
		return bookedSeats;
	}

	public int getMaxNumOfParticipants() {
		return maxNumOfParticipants;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public void setTravelAgency(TravelAgency travelAgency) {
		this.travelAgency = travelAgency;
	}
}
