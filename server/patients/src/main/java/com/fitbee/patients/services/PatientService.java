package com.fitbee.patients.services;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;

import java.util.Collection;

public interface PatientService {
    public abstract void createPatient(Patient patient);
    public abstract void updatePatient(int id, Patient patient);
    public abstract void deletePatient(int id);
    public abstract Collection<Patient> getPatients();
    public abstract Patient getPatientByID(int id);
}
