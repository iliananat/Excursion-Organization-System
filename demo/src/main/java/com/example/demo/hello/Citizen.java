package com.example.demo.hello;

import javax.persistence.*;

@Entity
public class Citizen extends User{
	@Id
	private String afm;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	
	
	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}	
}
