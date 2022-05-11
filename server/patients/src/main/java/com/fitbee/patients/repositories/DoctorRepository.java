package com.fitbee.patients.repositories;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
   // User findByUserName(String userName);
    Doctor findByName(String name);

}
