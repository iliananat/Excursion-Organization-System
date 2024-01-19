package com.example.demo.trip;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class TripSpecifications {
    public static Specification<Trip> withDepartureLocation(String departureLocation) {
        return (root, query, builder) -> departureLocation != null ?
                builder.like(builder.lower(root.get("departureLocation")), "%" + departureLocation.toLowerCase() + "%") :
                builder.conjunction();
    }

    public static Specification<Trip> withDestinationLocation(String destinationLocation) {
        return (root, query, builder) -> destinationLocation != null ?
                builder.like(builder.lower(root.get("destinationLocation")), "%" + destinationLocation.toLowerCase() + "%") :
                builder.conjunction();
    }

    public static Specification<Trip> withEndDate(LocalDate endDate) {
        return (root, query, builder) -> endDate != null ?
                builder.equal(root.get("endDate"), endDate) :
                builder.conjunction();
    }

    public static Specification<Trip> withStartDate(LocalDate startDate) {
        return (root, query, builder) -> startDate != null ?
                builder.equal(root.get("startDate"), startDate) :
                builder.conjunction();
    }
}
