package com.example.demo.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getCitizenTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getTravelAgencyTrips(String afm) {
        return tripRepository.findByTravelAgency_Afm(afm);
    }

    public void insertTrip(Trip trip) {
        tripRepository.save(trip);
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
                    comp = comp + temp.get(index).getDepartureLocation().charAt(j);
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
                    comp = comp + temp.get(index).getDestinationLocation().charAt(j);
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