package com.fitbee.patients.services;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;
    @Override
    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(int id, Patient patient) {
        Patient newP = patientRepository.findById(id).get();
        newP.setAddress(patient.getAddress());
        newP.setBedId(patient.getBedId());
        newP.setFirstName(patient.getFirstName());
        newP.setLastName(patient.getLastName());
        newP.setGender(patient.getGender());
        patientRepository.save(newP);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Collection<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByID(int id) {
        return patientRepository.findById(id).get();
    }
}
