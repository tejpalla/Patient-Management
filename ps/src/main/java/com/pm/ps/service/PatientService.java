package com.pm.ps.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pm.ps.dto.PatientRequestDTO;
import com.pm.ps.dto.PatientResponseDTO;
import com.pm.ps.exception.EmailAlreadyExistsException;
import com.pm.ps.exception.PatientNotFoundException;
import com.pm.ps.mapper.PatientMapper;
import com.pm.ps.model.Patient;
import com.pm.ps.repository.PatientRepository;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){

        List<Patient> patients =  patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOs = 
        patients.stream()
        .map(PatientMapper::toDTO)
        .toList();

        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email address already exists:  " + patientRequestDTO.getEmail());
        }

        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        
        //an email address must be unique
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id,
             PatientRequestDTO patientRequestDTO){
        
        Patient patient = patientRepository.findById(id).
             orElseThrow(() -> new PatientNotFoundException("Patient Not found with ID: " + id)); // returns optional meaning it could be null        
                
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
            throw new EmailAlreadyExistsException
                ("Email address already exists:  " + patientRequestDTO.getEmail());
        }
        
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
         
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
        
}
