package com.example.demo.hello;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trip {
	@Id
	private TravelAgency travelAgency;
	private LocalDate startDate;
	private LocalDate endDate;
	private String depLocation;
	private String destLocation;
	private String schedule;
	private String maxNumOfParticipants;

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
	
}
