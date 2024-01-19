package com.example.demo.trip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByTravelAgency_Afm(String afm);
}
