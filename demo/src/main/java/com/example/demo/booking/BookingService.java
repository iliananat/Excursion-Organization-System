package com.example.demo.booking;

import com.example.demo.trip.Trip;
import com.example.demo.trip.TripRepository;
import com.example.demo.user.Citizen;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    // Book Trip
    public boolean bookTrip(Long tripID, String citizenAFM, int numOfPeopleBooked) {
        Trip selectedTrip = tripRepository.findById(tripID).orElse(null);
        Citizen currCitizen = (Citizen) userRepository.findById(citizenAFM).orElse(null);

        // Validate inputs
        if (selectedTrip == null || currCitizen == null || numOfPeopleBooked < 0) {
            return false; // Invalid inputs
        }

        selectedTrip.setBookedSeats(selectedTrip.getBookedSeats() + numOfPeopleBooked);

        // Check if there are available seat
        if (selectedTrip.getAvailableSeats() >= 0) {
            // Update booked seats of trip
            tripRepository.save(selectedTrip);
            // Save booking
            Booking booking = new Booking(selectedTrip, currCitizen, numOfPeopleBooked);
            bookingRepository.save(booking);
            return true; // Booking successful
        } else {
            selectedTrip.setBookedSeats(selectedTrip.getBookedSeats() - numOfPeopleBooked);
            return false; // No seats
        }
    }
}