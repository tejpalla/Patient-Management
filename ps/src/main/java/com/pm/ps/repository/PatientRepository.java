package com.pm.ps.repository;

import com.pm.ps.model.*;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,UUID>{

    //summary 
    // This interface extends JpaRepository to provide CRUD operations for Patient entities.
    boolean existsByEmail(String email); // it is created by Spring Data JPA automatically based on the method name.
    boolean existsByEmailAndIdNot(String email, UUID id);
}
