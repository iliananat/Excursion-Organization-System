package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRepository extends JpaRepository<Trip, Long> {
}
