package com.example.demo.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("travel_agency")
public class TravelAgency extends User {
    private String name;
    private String owner;

    public TravelAgency() {
    }

    public TravelAgency(String afm, String password, String name, String owner) {
        super(afm, password);
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getUserType() {
        return "travel_agency";
    }

    @Override
    public boolean isValidRegistration() {
        // Check if required fields are not null or empty
        return this.afm != null && !this.afm.isEmpty() && isValidAfm(this.afm) &&
                this.password != null && !this.password.isEmpty() && isPasswordValid(this.password) &&
                this.name != null && !this.name.isEmpty() &&
                this.owner != null && !this.owner.isEmpty();
    }
}