package com.example.demo.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@DiscriminatorValue("citizen")
public class Citizen extends User {
    private String firstName;
    private String lastName;
    @Email(message = "Please provide a valid email address")
    private String email;

    public Citizen() {
    }

    public Citizen(String afm, String password, String firstName, String lastName, String email, PasswordEncoder passwordEncoder) {
        super(afm, password, passwordEncoder);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserType() {
        return "citizen";
    }

    @Override
    public boolean isValidRegistration() {
        // Check if required fields are not null or empty and if Afm is valid
        return this.afm != null && !this.afm.isEmpty() && isValidAfm(afm) &&
                this.password != null && !this.password.isEmpty() &&
                this.firstName != null && !this.firstName.isEmpty() &&
                this.lastName != null && !this.lastName.isEmpty() &&
                this.email != null && !this.email.isEmpty();
    }
}
