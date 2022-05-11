package com.fitbee.patients.services;

import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.repositories.AppointmentRepository;
import com.fitbee.patients.repositories.DoctorRepository;
import com.fitbee.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public void addAppointment(Appointment appointment,String name) {
        Appointment newAppointment = new Appointment();
        newAppointment.setDoctorName(appointment.getDoctorName());
        newAppointment.setDoctor(getDoctorByName(appointment.getDoctorName()));
        newAppointment.setPatient(getPatientByName(name));
        appointmentRepository.save(newAppointment);

    }
    public Doctor getDoctorByName(String name){
        return doctorRepository.findByName(name);
    }
    public Patient getPatientByName(String firstName){
        return patientRepository.findByFirstName(firstName);
    }
}
