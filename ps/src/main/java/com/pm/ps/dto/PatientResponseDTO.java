package com.pm.ps.dto;

public class PatientResponseDTO {
    private String id; // Unique identifier for the patient
    private String name; // Name of the patient
    private String email; // Email of the patient
    private String address; // Address of the patient
    private String dateOfBirth; // Date of birth of the patient

    public PatientResponseDTO() {
        // Default constructor
    }
    
    public PatientResponseDTO(String id, String name, String email, String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth; 
    }
}
