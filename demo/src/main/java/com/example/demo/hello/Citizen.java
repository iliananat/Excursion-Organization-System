package com.example.demo.hello;

import javax.persistence.*;

@Entity
public class Citizen extends User{

	private String first_name;
	private String last_name;
	private String email;
	
	public Citizen(String afm, String password, String first_name, String last_name, String email) {
		super(afm, password);
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	
	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}	
}
