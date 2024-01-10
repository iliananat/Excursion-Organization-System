package com.example.demo.hello;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Booking {
	private ArrayList<Trip> trips;
	
	public Booking(){}
	
	public Booking(ArrayList<Trip> t) {
		trips = t;
	}
	
	
	public ArrayList<Trip> tripSearch(String depLoc, String destLoc, LocalDate startdt, LocalDate enddt){
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
	
}
