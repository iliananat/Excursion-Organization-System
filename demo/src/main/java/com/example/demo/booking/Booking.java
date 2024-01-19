package com.example.demo.booking;

import com.example.demo.user.Citizen;
import com.example.demo.trip.Trip;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Citizen citizen;
    private int seats;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Trip trip;

    public Booking() {
    }

    public Booking(Trip trip, Citizen citizen, int seats) {
        this.trip = trip;
        this.citizen = citizen;
        this.seats = seats;
    }

    public Citizen getCitizen() {
        return this.citizen;
    }
}
