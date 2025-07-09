package com.pm.ps.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.ps.dto.PatientRequestDTO;
import com.pm.ps.dto.PatientResponseDTO;
import com.pm.ps.dto.validators.CreatePatientValidationGroup;
import com.pm.ps.model.Patient;
import com.pm.ps.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController // Indicates that this class is a REST controller
@RequestMapping("/patients") // Base URL for patient-related endpoints
@Tag(name = "Patient", description="API for managing Patients")
public class PatientController {
    
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary="Get Patients") //it makes to appear on swagger ui
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok(patients); // Returns a 200 OK response with the list of patients
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient
        (@Validated({Default.class, CreatePatientValidationGroup.class}) 
        @RequestBody PatientRequestDTO patientRequestDTO) { //@Valid makes sure that all the validations are done on requestdto
        
            PatientResponseDTO createdPatient = patientService.
            createPatient(patientRequestDTO);
        
        return ResponseEntity.status(201).body(createdPatient);  // Returns a 201 Created response with the created patient
    }

    //localhost:4000/patients/1212123123-123123-123123
    @PutMapping("/{id}")//updating an entity
    @Operation(summary = "Update a Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient
            (@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
            //tells spring to validate request using all the defaults we specified in the DTO 
        PatientResponseDTO patientResponseDTO = 
                patientService.updatePatient(id, patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<Void> deletePatient
                (@PathVariable UUID id){

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build(); //returns status code 204 means no content
    }

}
