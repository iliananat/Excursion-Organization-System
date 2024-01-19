package com.example.demo.trip;

import com.example.demo.user.TravelAgency;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private int bookedSeats;
	@Column(nullable = false)
	private String departureLocation;
	@Column(nullable = false)
	private String destinationLocation;
	@Column(nullable = false)
	private LocalDate endDate;
	@Column(nullable = false)
	private int maximumSeats;
	@Column(nullable = false)
	private String schedule;
	@Column(nullable = false)
	private LocalDate startDate;
	@ManyToOne
    @JoinColumn(name = "travel_agency_afm")
	private TravelAgency travelAgency;

	public Trip(){}
	
	public Trip(String departureLocation,
				String destinationLocation,
				LocalDate endDate,
				int maximumSeats,
				String schedule,
				LocalDate startDate,
				TravelAgency travelAgency) {
		this.bookedSeats = 0;
		this.departureLocation = departureLocation;
		this.destinationLocation = destinationLocation;
		this.endDate = endDate;
		this.maximumSeats = maximumSeats;
		this.schedule = schedule;
		this.startDate = startDate;
		this.travelAgency = travelAgency;
	}

	public int getAvailableSeats() {
		return this.maximumSeats - this.bookedSeats;
	}

	public int getBookedSeats() {
		return this.bookedSeats;
	}

	public String getDepartureLocation() {
		return this.departureLocation;
	}

	public String getDestinationLocation() {
		return this.destinationLocation;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public TravelAgency getTravelAgency() {
		return this.travelAgency;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public void setTravelAgency(TravelAgency travelAgency) {
		this.travelAgency = travelAgency;
	}
}
