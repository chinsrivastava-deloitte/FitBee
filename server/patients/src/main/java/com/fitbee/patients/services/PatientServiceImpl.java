package com.fitbee.patients.services;

import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.repositories.AppointmentRepository;
import com.fitbee.patients.repositories.PatientRepository;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
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


    @Override
    public List<CaseHistoryDto> getUserCaseHistory(String firstName){

        List<Appointment>patientAppointments=patientRepository.findByFirstName(firstName).getAppointments();
        return patientAppointments.stream().map(this::convertCaseHistoryDto).collect(Collectors.toList());

    };

    private CaseHistoryDto convertCaseHistoryDto(Appointment appointment){
        CaseHistoryDto caseHistoryDto= new CaseHistoryDto();
        caseHistoryDto.setDoctorName(appointment.getDoctor().getName());
        caseHistoryDto.setDiagnosis(appointment.getDiagnosis());
        caseHistoryDto.setPrescription(appointment.getPrescription());
        caseHistoryDto.setDate(appointment.getDate());
        return caseHistoryDto;
    }


}
