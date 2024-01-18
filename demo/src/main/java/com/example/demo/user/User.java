package com.example.demo.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    protected String afm;
    @Column(nullable = false)
    @NotNull(message = "The password cannot be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected int loginAttempts;

    public User() {
    }

    public User(String afm, String password) {
        this.afm = afm;
        this.password = password;
        this.loginAttempts = 0;
    }

    @JsonIgnore
    public abstract boolean isValidRegistration();

    public String getAfm() {
        return this.afm;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public boolean isPasswordCorrect(String providedPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(providedPassword, this.password);
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?!.* )(?=.*[!@#$%^&*.])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*.]{7,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidAfm(String afm) {
        // Check if the AFM is not null and has exactly 9 digits
        boolean isNineDigits = afm != null && afm.length() == 9 && afm.matches("\\d+");
        if (isNineDigits) {
            int multiplier = 2;
            int sum = 0;
            for (int i = afm.length() - 2; i > -1; i--) {
                sum += Character.getNumericValue(afm.charAt(i)) * multiplier;
                multiplier *= 2;
            }
            int modulo = sum % 11;
            return (modulo == 10 && afm.charAt(afm.length() - 1) == '0') ||
                    (modulo != 10 && modulo == Character.getNumericValue(afm.charAt(afm.length() - 1)));
        } else {
            return false;
        }
    }
}
