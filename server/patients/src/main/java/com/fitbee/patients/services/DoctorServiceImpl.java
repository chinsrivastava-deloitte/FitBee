package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.AppointmentType;
import com.fitbee.patients.repositories.AppointmentRepository;
import com.fitbee.patients.repositories.DoctorRepository;
import com.fitbee.patients.repositories.PatientRepository;
import com.fitbee.patients.utils.dto.PrescriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.print.Doc;
import java.util.Collection;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(int id, Doctor doctor) throws IdNotFoundException {
        if(doctorRepository.existsById(id)) {
            Doctor oldDoctor = doctorRepository.findById(id).get();
            oldDoctor.setExperience(doctor.getExperience());
            oldDoctor.setExpertise(doctor.getExpertise());
            oldDoctor.setName(doctor.getName());
            oldDoctor.setIsVerified(doctor.getIsVerified());
            oldDoctor.setResearch(doctor.getResearch());
            oldDoctor.setQualification(doctor.getQualification());
            doctorRepository.save(oldDoctor);
        }
        else
            throw new IdNotFoundException("doctor's id not present in database");

    }

    @Override
    public void deleteDoctor(int id) throws IdNotFoundException{
        if(doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        }
        else
            throw new IdNotFoundException("doctor's id is not present");
    }

    @Override
    public Collection<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorByID(int id) throws IdNotFoundException {
        if(doctorRepository.existsById(id))
            return doctorRepository.findById(id).get();
        else
            throw new IdNotFoundException("doctor's id is not present");
    }
//    @Override
//    public Doctor getDoctorByName(String name){
//        return doctorRepository.findByName(name);
//    }
    @Override
    public void addPrescription(PrescriptionDto prescriptionDto){

        Appointment appointment=appointmentRepository.findById(prescriptionDto.getAppointmentId()).get();
        appointment.setDiagnosis(prescriptionDto.getDiagnosis());
        appointment.setPrescription(prescriptionDto.getPrescription());
        appointment.setAppointmentType(prescriptionDto.getAppointmentType());
        appointmentRepository.save(appointment);

    }
    
    @Override
    public void appointmentCheckout(int appointmentId){
        Appointment appointment=appointmentRepository.getById(appointmentId);
        appointment.setAppointmentStatus(AppointmentEnum.COMPLETED);
        appointmentRepository.save(appointment);
    }

    @Override
    public void setPatientStatus(int appointmentId,String status){
        Appointment appointment= appointmentRepository.getById(appointmentId);
        if(status.equalsIgnoreCase("Critical")){
            appointment.setAppointmentType(AppointmentType.CRITICAL);
        }
        else{
            appointment.setAppointmentType(AppointmentType.REGULAR);
        }
        appointmentRepository.save(appointment);
    }

}
