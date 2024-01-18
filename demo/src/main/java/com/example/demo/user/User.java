package com.example.demo.user;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
	@Id
	protected String afm;
	@JsonIgnore
	protected String password;
	
	public User(){}
	
	public User (String afm, String password) {
		this.afm = afm;
		this.password = password;
	}
	
	@JsonIgnore
	public abstract boolean isValidRegistration();
	public abstract String getUserType();
	
	public String getAfm() {
		return afm;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public boolean isValidAfm(String afm) {
	    // Check if the AFM is not null and has exactly 9 digits
	    return afm != null && afm.length() == 9 && afm.matches("\\d+");
	}


}
