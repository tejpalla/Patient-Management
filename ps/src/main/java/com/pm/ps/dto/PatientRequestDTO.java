package com.pm.ps.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.pm.ps.dto.validators.CreatePatientValidationGroup;

import jakarta.validation.constraints.*;

public class PatientRequestDTO {

    @NotBlank // Ensures that the name field is not blank
    @Size(max = 100, message ="name cannot exceed 100 character") // Limits the length of the name to 100 characters
    private String name;

    @NotBlank
    @Email(message="Email should be valid") // Ensures that the email is valid
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message="Date of birth is required")
    private String dateOfBirth; // Date of birth of the patient in String format    

    @NotBlank(groups = CreatePatientValidationGroup.class ,message = "Registered date is required")
    private String registeredDate; // Date when the patient registered in String format

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

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteredDate(){
        return this.registeredDate;
    }
}
