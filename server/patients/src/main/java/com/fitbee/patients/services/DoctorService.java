package com.fitbee.patients.services;

import com.fitbee.patients.models.Doctor;

import java.util.Collection;
import java.util.Optional;

public interface DoctorService {
    public abstract void createDoctor(Doctor doctor);
    public abstract void updateDoctor(int id, Doctor doctor);
    public abstract void deleteDoctor(int id);
    public abstract Collection<Doctor> getDoctors();
    public abstract Doctor getDoctorByID(int id);
}
