package com.example.demo.hello;

import javax.persistence.*;

@Entity
@DiscriminatorValue("citizen")
public class Citizen extends User{

	private String firstName;
	private String lastName;
	private String email;
	
	public Citizen(){}
	
	public Citizen(String afm, String password, String firstName, String lastName, String email) {
		super(afm, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isValidRegistration() {
	    // Check if required fields are not null or empty
	    return this.afm != null && !this.afm.isEmpty() && isValidAfm(afm) &&
	           this.password != null && !this.password.isEmpty() &&
	           this.firstName != null && !this.firstName.isEmpty() &&
	           this.lastName != null && !this.lastName.isEmpty() &&
	           this.email != null && !this.email.isEmpty();
	}

}
