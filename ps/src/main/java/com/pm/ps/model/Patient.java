package com.pm.ps.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generate a unique ID for each patient
    private UUID id; // Unique identifier for the patient

    @NotNull // Ensure that the name field is not null
    private String name; // Name of the patient

    @NotNull
    @Email
    @Column(unique = true) // Ensure that the email is unique and valid
    private String email; // Email of the patient

    @NotNull
    private String address;

    @NotNull
    private LocalDate dateOfBirth; // Date of birth of the patient

    @NotNull
    private LocalDate registeredDate; // Date when the patient registered

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return this.registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

}
