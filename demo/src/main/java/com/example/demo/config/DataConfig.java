package com.example.demo.config;

import com.example.demo.booking.BookingRequest;
import com.example.demo.booking.BookingService;
import com.example.demo.trip.Trip;
import com.example.demo.trip.TripService;
import com.example.demo.user.Citizen;
import com.example.demo.user.TravelAgency;
import com.example.demo.user.UserService;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;

import org.springframework.beans.factory.annotation.*;

import java.time.LocalDate;

@Configuration
public class DataConfig implements CommandLineRunner {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Citizen citizen = new Citizen("801962655", "Citizen1!", "Μαρία", "Πανταζή", "maria.pa@example.com");
        Citizen citizen2 = new Citizen("998934837", "Citizen2!", "Ηλιάνα", "Νάτσιου", "ili.na@example.com");
        Citizen citizen3 = new Citizen("999940368", "Citizen3!", "Δημήτρης", "Καλαϊτζίδης", "kalai@example.com");
        Citizen citizen4 = new Citizen("998902920", "Citizen4!", "Γιώργος", "Χατζηπαρασκευάς", "xatzi@example.com");
        TravelAgency travelAgency = new TravelAgency("800449340", "Travel1!", "Adventure Tours", "Δώρα Πέτρου");
        TravelAgency travelAgency2 = new TravelAgency("800491751", "Travel2!", "Adventure Trips", "Πέτρος Δημητρίου");
        TravelAgency travelAgency3 = new TravelAgency("801508680", "Travel3!", "WeTravel", "Κώστας Παπαδόπουλος");
        userService.registerUser(citizen);
        userService.registerUser(citizen2);
        userService.registerUser(citizen3);
        userService.registerUser(citizen4);
        userService.registerUser(travelAgency);
        userService.registerUser(travelAgency2);
        userService.registerUser(travelAgency3);

        Trip trip = new Trip("Αθήνα", "Κρήτη", LocalDate.of(2024, 1, 20), 50, "<h2><strong>1η μέρα</strong></h2><ul><li>9:00 Αναχώρηση από αεροδρόμιο Αθήνας</li><li>10:00 Άφιξη στο Ηράκλειο Κρήτης</li><li>11:00 Επίσκεψη στο Αρχαιολογικό Μουσείο Ηρακλείου</li><li>12:30 Check-in στο ξενοδοχείο</li><li>17:00 Επίσκεψη στο Μουσείο Φυσικής Ιστορίας</li></ul><h2><strong>2η μέρα</strong></h2><ul><li>10:00 Επίσκεψη στο Μινωικό Ανάκτορο της Κνωσού</li><li>13:00 Επίσκεψη στο ενυδρείο CRETAquarium</li><li>14:30 Επιστροφή στο ξενοδοχείο για φαγητό και ξεκούραση</li><li>17:00 Επίσκεψη στον τάφο του Νίκου Καζαντζάκη</li></ul><h2><strong>3η μέρα</strong></h2><ul><li>10:00 Επίσκεψη στον Ιερό Ναό Αγίου Τίτου</li><li>12:00 Επίσκεψη στο Ενετικό λιμάνι του Ηρακλείου</li></ul><h2><strong>4η μέρα</strong></h2><ul><li>12:00 Check-out από ξενοδοχείο</li><li>15:00 Αναχώρηση από Ηράκλειο</li><li>16:00 Άφιξη στο αεροδρόμιο Αθήνας</li></ul>", LocalDate.of(2024, 1, 15), travelAgency);
        Trip trip2 = new Trip("Αθήνα", "Πελοπόννησος", LocalDate.of(2024, 1, 25), 20, "<h2><strong>1η μέρα</strong></h2><ul><li>10:00 Αναχώρηση από Αθήνα</li><li>12:00 Άφιξη στην Επίδαυρο</li><li>13:30 Αναχώρηση από Επίδαυρο</li><li>14:00 Άφιξη στο Ναύπλιο και Check-in στο ξενοδοχείο</li><li>18:30 Επίσκεψη στο Αρχαιολογικό Μουσείο Ναυπλίου</li></ul><h2><strong>2η μέρα</strong></h2><ul><li>10:00 Επίσκεψη στο Πελοποννησιακό Λαογραφικό Ίδρυμα «Βασίλειος Παπαντωνίου»</li><li>12:30 Επίσκεψη στο Μπούρτζι</li><li>14:00 Επιστροφή στο Ναύπλιο για φαγητό και ξεκούραση</li><li>17:00 Επίσκεψη στο Φρούριο Παλαμηδίου</li></ul><h2><strong>3η μέρα</strong></h2><ul><li>10:30 Αναχώρηση για Μυκήνες</li><li>11:00 Άφιξη στις Μυκήνες</li><li>18:00 Επιστροφή στο Ναύπλιο</li></ul><h2><strong>4η μέρα</strong></h2><ul><li>10:00 Επίσκεψη στο Κάστρο Μεθώνης</li><li>12:30 Επίσκεψη στον Αρχαιολογικό χώρο Αρχαίας Μεσσήνης</li><li>14:00 Διάλειμμα για φαγητό</li><li>17:00 Επίσκεψη στην Διώρυγα της Κορίνθου</li></ul><h2><strong>5η μέρα</strong></h2><ul><li>12:00 Check-out από ξενοδοχείο</li><li>15:00 Αναχώρηση από Ναύπλιο</li><li>17:00 Άφιξη στην Αθήνα</li></ul>", LocalDate.of(2024, 1, 20), travelAgency2);
        Trip trip3 = new Trip("Θεσσαλονίκη", "Αθήνα", LocalDate.of(2024, 1, 27), 35, "<h2><strong>1η μέρα</strong></h2><ul><li>9:00 Αναχώρηση από Θεσσαλονίκη</li><li>15:00 Άφιξη στην Αθήνα</li><li>15:00 Check-in στο ξενοδοχείο</li><li>17:00 Tour bus</li><li>18:30 Επίσκεψη στο Μουσείο της Ακρόπολης</li></ul><h2><strong>2η μέρα</strong></h2><ul><li>10:00 Επίσκεψη στην Ακρόπολη</li><li>13:30 Διάλειμμα για φαγητό</li><li>16:00 Επίσκεψη στο Αττικό ζωολογικό πάρκο</li></ul><h2><strong>3η μέρα</strong></h2><ul><li>10:00 Αναχώρηση για Δελφούς</li><li>18:00 Επιστροφή στην Αθήνα</li></ul><h2><strong>4η μέρα</strong></h2><ul><li>12:00 Check-out από ξενοδοχείο</li><li>15:00 Αναχώρηση από Αθήνα</li><li>19:00 Άφιξη στην Θεσσαλονίκη</li></ul>", LocalDate.of(2024, 1, 22), travelAgency3);
        tripService.insertTrip(trip);
        tripService.insertTrip(trip2);
        tripService.insertTrip(trip3);

        bookingService.insertBooking(new BookingRequest(5, trip.getID()), citizen);
        bookingService.insertBooking(new BookingRequest(5, trip.getID()), citizen2);
        bookingService.insertBooking(new BookingRequest(6, trip2.getID()), citizen);
        bookingService.insertBooking(new BookingRequest(13, trip2.getID()), citizen2);
        bookingService.insertBooking(new BookingRequest(5, trip2.getID()), citizen3);
        bookingService.insertBooking(new BookingRequest(1, trip2.getID()), citizen3);
        bookingService.insertBooking(new BookingRequest(5, trip3.getID()), citizen4);
    }
}
