package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.AppointmentType;
import com.fitbee.patients.repositories.AppointmentRepository;
import com.fitbee.patients.repositories.DoctorRepository;
import com.fitbee.patients.repositories.PatientRepository;
import com.fitbee.patients.utils.dto.AppointmentDto;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    @Override
    public void addAppointment(AppointmentDto appointmentDto) throws IdNotFoundException, DateException {
        Appointment appointment = new Appointment();
        if(doctorRepository.existsById(appointmentDto.getDoctorId()))
            appointment.setDoctor(doctorRepository.findById(appointmentDto.getDoctorId()).get());
        else
            throw new IdNotFoundException("doctor's id not present in database");
        if(patientRepository.existsById(appointmentDto.getPatientId()))
            appointment.setPatient(patientRepository.findById(appointmentDto.getPatientId()).get());
        else
            throw new IdNotFoundException("patient's id not present in database");
//        if(appointmentDto.getDate().compareTo(java.time.LocalDate.now()){
//            throw new DateException(" entered date must be more than current date");
//        }
        appointment.setDate(appointmentDto.getDate());
        appointment.setStartTime(appointmentDto.getStartTime());
        appointment.setEndTime(appointmentDto.getEndTime());
        appointment.setAppointmentType(AppointmentType.REGULAR);
        appointment.setAppointmentStatus(AppointmentEnum.NOT_STARTED);
        appointmentRepository.save(appointment);
    }
    @Override
    public List<Appointment> getAppointments(){
        return appointmentRepository.findAll();
    }

    public Doctor getDoctorByName(String name){
        return doctorRepository.findByName(name);
    }
    public Patient getPatientByName(String firstName){
        return patientRepository.findByFirstName(firstName);
    }


}
