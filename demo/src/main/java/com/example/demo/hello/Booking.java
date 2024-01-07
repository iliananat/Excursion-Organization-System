package com.example.demo.hello;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Booking {
	private ArrayList<Trip> trips;
	
	public Booking(ArrayList<Trip> t) {
		trips = t;
	}
	
	public ArrayList<Trip> tripSearch(String depLoc, String destLoc, LocalDate startdt, LocalDate enddt, String travelag){
		ArrayList<Trip> temp = new ArrayList<>();
		temp = trips;
		
		if(depLoc!=null) {
			for(int i=0;i<temp.size();i++) {
				if(!temp.get(i).getDepLocation().equals(depLoc)) {
					temp.remove(i);			
				}
			}
		}
		if(destLoc!=null) {
			for(int i=0;i<temp.size();i++) {
				if(!temp.get(i).getDestLocation().equals(destLoc)) {
					temp.remove(i);
				}
			}
		}
		if(startdt!=null) {
			for(int i=0;i<temp.size();i++) {
				if(!temp.get(i).getStartDate().equals(startdt)) {
					temp.remove(i);
				}
			}
		}
		if(enddt!=null) {
			for(int i=0;i<temp.size();i++) {
				if(!temp.get(i).getEndDate().equals(enddt)) {
					temp.remove(i);
				}
			}
		}
		if(travelag!=null) {
			//complete this
		}
		
		return temp;
	}
	
}
