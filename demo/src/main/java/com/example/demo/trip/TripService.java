package com.example.demo.trip;

import com.example.demo.booking.Booking;
import com.example.demo.booking.BookingRepository;
import com.example.demo.user.Citizen;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    public void addTrip(Trip t) {
        tripRepository.save(t);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Booking> getMyBookings(String afm) {
        Citizen citizen = (Citizen) userRepository.findById(afm).orElse(null);
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> myBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getBookedCitizen().equals(citizen)) {
                myBookings.add(booking);
            }
        }
        return myBookings;
    }

    public List<Trip> getTravelAgencyTrips(String afm) {
        TravelAgency travelAgency = (TravelAgency) userRepository.findById(afm).orElse(null);
        List<Trip> allTrips = tripRepository.findAll();
        List<Trip> travelAgencyTrips = new ArrayList<>();
        for (Trip trip : allTrips) {
            if (trip.getTravelAgency().getAfm().equals(travelAgency.getAfm())) {
                travelAgencyTrips.add(trip);
            }
        }
        return travelAgencyTrips;
    }

    public List<Trip> searchTrips(String depLoc, String destLoc, String startDate, String endDate) {
        List<Trip> trips = tripRepository.findAll();
        List<Trip> temp = trips;

        if (startDate != null) {
            LocalDate startdt = LocalDate.parse(startDate);
            int index = 0;
            while (index < temp.size()) {
                if (!temp.get(index).getStartDate().equals(startdt)) {
                    temp.remove(index);
                } else {
                    index++;
                }
            }
        }
        if (endDate != null) {
            LocalDate enddt = LocalDate.parse(endDate);
            int index = 0;
            while (index < temp.size()) {
                if (!temp.get(index).getEndDate().equals(enddt)) {
                    temp.remove(index);
                } else {
                    index++;
                }
            }
        }

        if (depLoc != null) {
            int locLength = depLoc.length();
            int index = 0;
            while (index < temp.size()) {
                String comp = "";
                for (int j = 0; j < locLength; j++) {
                    comp = comp + temp.get(index).getDepLocation().charAt(j);
                }
                if (!comp.toLowerCase().equals(depLoc.toLowerCase())) {
                    temp.remove(index);
                } else {
                    index++;
                }
            }
        }

        if (destLoc != null) {
            int destLength = destLoc.length();
            int index = 0;
            while (index < temp.size()) {
                String comp = "";
                for (int j = 0; j < destLength; j++) {
                    comp = comp + temp.get(index).getDestLocation().charAt(j);
                }
                if (!comp.toLowerCase().equals(destLoc.toLowerCase())) {
                    temp.remove(index);
                } else {
                    index++;
                }
            }
        }

        return temp;
    }
}