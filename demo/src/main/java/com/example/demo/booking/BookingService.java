package com.example.demo.booking;

import com.example.demo.config.InfoResponse;
import com.example.demo.trip.Trip;
import com.example.demo.trip.TripRepository;
import com.example.demo.user.Citizen;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    public ResponseEntity<?> insertBooking(BookingRequest bookingRequest, User user) {
        int seats = bookingRequest.getSeats();
        if (seats < 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Ο αριθμός συμμετοχών πρέπει να είναι μεγαλύτερος του μηδενός"));
        }
        Trip trip = tripRepository.findById(bookingRequest.getTripID()).orElse(null);
        if (trip == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Δεν υπάρχει η εκδρομή"));
        }
        if (trip.getAvailableSeats() - seats < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Δεν υπάρχουν διαθέσιμες θέσεις"));
        }
        LocalDate today = LocalDate.now();
        if (today.isAfter(trip.getStartDate())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InfoResponse("Δεν μπορείτε πλέον να κλείσετε θέση"));
        }
        trip.setBookedSeats(trip.getBookedSeats() + seats);
        tripRepository.save(trip);
        Booking booking = new Booking(trip, (Citizen) user, seats);
        bookingRepository.save(booking);
        return ResponseEntity.ok().body(new InfoResponse("Ολοκληρώθηκε με επιτυχία"));
    }
}