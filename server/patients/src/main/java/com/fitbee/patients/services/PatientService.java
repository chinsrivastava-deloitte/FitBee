package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import com.fitbee.patients.utils.dto.PatientDto;

import java.util.Collection;
import java.util.List;

public interface PatientService {
    public abstract void createPatient(Patient patient);
    public abstract void updatePatient(int id, Patient patient) throws IdNotFoundException;
    public abstract void deletePatient(Integer id) throws IdNotFoundException;
    public abstract Collection<Patient> getPatients();
    public abstract Patient getPatientByID(int id) throws IdNotFoundException;
    public abstract int  fetchPatientByUser(int userId);
    public abstract void addPatient(PatientDto patientDto);
 
    public abstract List<CaseHistoryDto>getUserCaseHistory(String firstName);

}
