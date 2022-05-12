package com.fitbee.patients.repositories;

import com.fitbee.patients.models.Patient;
import com.fitbee.patients.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Patient findByFirstName(String firstName);
    //  Patient findByName(String name);
    Patient findByUser(User user);
}
