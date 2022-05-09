package com.fitbee.patients.repositories;

import com.fitbee.patients.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
