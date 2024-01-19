package com.example.demo.trip;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getTrips(String departureLocation, String destinationLocation, LocalDate endDate, LocalDate startDate, User user) {
        Specification<Trip> spec = null;
        Specification<Trip> specification1 = TripSpecifications.withDepartureLocation(departureLocation);
        Specification<Trip> specification2 = TripSpecifications.withDestinationLocation(destinationLocation);
        Specification<Trip> specification3 = TripSpecifications.withEndDate(endDate);
        Specification<Trip> specification4 = TripSpecifications.withStartDate(startDate);
        Specification<Trip> specification5 = TripSpecifications.withTravelAgencyAfm(user.getAfm());

        if (departureLocation != null) {
            spec = Specification.where(specification1);
        }

        if (destinationLocation != null) {
            spec = (spec == null) ? Specification.where(specification2) : spec.and(specification2);
        }

        if (endDate != null) {
            spec = (spec == null) ? Specification.where(specification3) : spec.and(specification3);
        }

        if (startDate != null) {
            spec = (spec == null) ? Specification.where(specification4) : spec.and(specification4);
        }

        if (user.getUserType().equals("travel_agency")) {
            spec = (spec == null) ? Specification.where(specification5) : spec.and(specification5);
        }

        return tripRepository.findAll(spec);
    }

    public void insertTrip(Trip trip) {
        tripRepository.save(trip);
    }
}