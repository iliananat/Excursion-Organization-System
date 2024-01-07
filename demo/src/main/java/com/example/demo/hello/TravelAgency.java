package com.example.demo.hello;

import javax.persistence.*;

@Entity
public class TravelAgency extends User{
	@Id
	private String afm;
	private String name;
	private String owner;
	private String password;
	
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
