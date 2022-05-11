package com.fitbee.patients.services;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.utils.dto.CaseHistoryDto;

import java.util.Collection;
import java.util.List;

public interface PatientService {
    public abstract void createPatient(Patient patient);
    public abstract void updatePatient(int id, Patient patient);
    public abstract void deletePatient(int id);
    public abstract Collection<Patient> getPatients();
    public abstract Patient getPatientByID(int id);

 
    public abstract List<CaseHistoryDto>getUserCaseHistory(String firstName);

}
