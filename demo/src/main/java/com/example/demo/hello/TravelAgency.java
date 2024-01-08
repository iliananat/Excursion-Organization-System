package com.example.demo.hello;

import javax.persistence.*;

@Entity


public class TravelAgency extends User{
	private String name;
	private String owner;
	
	
	public TravelAgency(String afm, String password, String name, String owner) {
		super(afm, password);
		this.name = name;
		this.owner = owner;
	}
	
	public String getAfm() {
		return afm;
	}
	
	public String getName() {
		return name;
	}
	public String getOwner() {
		return owner;
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}
	
}
