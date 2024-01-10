package com.example.demo.hello;

import javax.persistence.*;

@Entity
public class TravelAgency extends User {
    private String name;
    private String owner;

    public TravelAgency() {}

    public TravelAgency(String afm, String password, String name, String owner) {
        super(afm, password);
        this.name = name;
        this.owner = owner;
    }

    @Override
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
    public boolean isValidRegistration() {
        // Check if required fields are not null or empty
        return this.afm != null && !this.afm.isEmpty() && isValidAfm(afm) &&
               this.password != null && !this.password.isEmpty() &&
               this.name != null && !this.name.isEmpty() &&
               this.owner != null && !this.owner.isEmpty();
    }


}