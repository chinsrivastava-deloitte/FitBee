package com.fitbee.patients.repositories;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.DoctorSlot;
import com.fitbee.patients.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorSlotRepository extends JpaRepository<DoctorSlot,Integer> {
    DoctorSlot findBySlotAndDoctor(Slot slot, Doctor doctor);
    List<DoctorSlot> findByDoctorDoctorId(int doctorId);
}
