package com.example.demo.hello;

import javax.persistence.*;

public abstract class User {
	@Id
	protected String afm;
	protected String password;
	
	public User(){}
	
	public User (String afm, String password) {
		this.afm = afm;
		this.password = password;
	}
	
	
	public abstract void register();
	
	public String getAfm() {
		return afm;
	}
	
	
	public void login() {
		
	}
}
