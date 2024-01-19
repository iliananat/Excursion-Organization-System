package com.example.demo.booking;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {
    private final int numOfPeopleBooked;
    private final Long tripID;

    public BookingRequest(int numOfPeopleBooked, Long tripID) {
        this.numOfPeopleBooked = numOfPeopleBooked;
        this.tripID = tripID;
    }

    public int getNumOfPeopleBooked() {
        return numOfPeopleBooked;
    }

    public Long getTripID() {
        return tripID;
    }
}
