package com.example.demo.booking;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {
    private final int seats;
    private final Long tripID;

    public BookingRequest(int seats, Long tripID) {
        this.seats = seats;
        this.tripID = tripID;
    }

    public int getSeats() {
        return seats;
    }

    public Long getTripID() {
        return tripID;
    }
}
