package com.example.demo.hello;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private ArrayList<Trip> trips;
	private Citizen citizenBooked;
	private Trip tripBooked;
	
	public Booking(){}
	
/*	public Booking(ArrayList<Trip> t) {
		trips = t;
	}*/
	
	public Booking(Trip tripBooked, Citizen citizenBooked) {
		this.tripBooked = tripBooked;
		this.citizenBooked = citizenBooked;
	}

	public Trip getTrip() {
		// TODO Auto-generated method stub
		return tripBooked;
	}
	
	public Citizen getBookedCitizen() {
		return citizenBooked;
	}
	
	
/*	public ArrayList<Trip> tripSearch(String depLoc, String destLoc, LocalDate startdt, LocalDate enddt){
		ArrayList<Trip> temp = new ArrayList<>();
		temp = trips;
		
		if(depLoc!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getDepLocation().equals(depLoc)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}

		if(destLoc!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getDestLocation().equals(destLoc)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		
		if(startdt!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getStartDate().equals(startdt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		if(enddt!=null) {
			int index=0;
			while(index<temp.size()) {
				if(!temp.get(index).getEndDate().equals(enddt)) {
					temp.remove(index);
				}else {
					index++;
				}
			}
		}
		return temp;
	}

	public Trip getTrip() {
		return tripBooked;
	}
	
	public Citizen getBookedCitizen() {
		return citizenBooked;
	}*/

	
}
