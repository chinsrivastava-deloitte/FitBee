package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.*;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.AppointmentType;
import com.fitbee.patients.models.enums.SlotStatus;
import com.fitbee.patients.repositories.*;
import com.fitbee.patients.utils.dto.AppointmentDto;
import com.fitbee.patients.utils.dto.PreviousAppointmentDto;
import com.fitbee.patients.utils.dto.RescheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorSlotRepository doctorSlotRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    ApptRepository apptRepository;

    @Override
    public void addAppointment(AppointmentDto appointmentDto) throws IdNotFoundException, DateException {
        Appointment appointment = new Appointment();
        if (doctorRepository.existsById(appointmentDto.getDoctorId()))
            appointment.setDoctor(doctorRepository.findById(appointmentDto.getDoctorId()).get());
        else
            throw new IdNotFoundException("doctor's id not present in database");
        if (patientRepository.existsById(appointmentDto.getPatientId()))
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
    public void addappointment(AppointmentDto appointmentDto){
        Appt appt = new Appt();
        appt.setPatient(patientRepository.findById(appointmentDto.getPatientId()).get());
        Date d = appointmentDto.getDate();
        Date st = appointmentDto.getStartTime();

        Slot s = slotRepository.findByDateAndFromTime(d,st);
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId()).get();
        DoctorSlot ds = doctorSlotRepository.findBySlotAndDoctor(s,doctor);

        appt.setDoctorSlot(ds);
        ds.setIsOccupied(SlotStatus.OCCUPIED);
        doctorSlotRepository.save(ds);
        apptRepository.save(appt);

    }
    public List<Slot> getAllSlots(){
        return slotRepository.findAll();
    }
    public List<DoctorSlot> getDoctorSlots(){
        return doctorSlotRepository.findAll();
    }
    @Override
    public List<Appt> getAppointments() {
        return apptRepository.findAll();
    }

    public Doctor getDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }

    public Patient getPatientByName(String firstName) {
        return patientRepository.findByFirstName(firstName);
    }

    public List<PreviousAppointmentDto> getPreviousAppointments(int patientId) {
        List<Appointment> appointmentList = patientRepository.findById(patientId).get().getAppointments();
        List<PreviousAppointmentDto> previousAppointmentList = new ArrayList<>();
        for (Appointment a : appointmentList) {
            if (a.getAppointmentStatus() == AppointmentEnum.COMPLETED) {
                PreviousAppointmentDto previousAppointmentDto = new PreviousAppointmentDto();
                previousAppointmentDto.setDoctorName(a.getDoctor().getName());
                previousAppointmentDto.setDate(a.getDate());
                previousAppointmentDto.setTime(a.getStartTime());
                previousAppointmentDto.setDescription(a.getAppointmentType());
                previousAppointmentList.add(previousAppointmentDto);
            }

        }
        return previousAppointmentList;

    }

    @Override
    public void rescheduleAppointment(RescheduleDto rescheduleDto) {
        Appointment a = appointmentRepository.findById(rescheduleDto.getAppointmentId()).get();
        a.setStartTime(rescheduleDto.getStartTime());
        a.setEndTime(rescheduleDto.getEndTime());
        a.setDate(rescheduleDto.getDate());
        appointmentRepository.save(a);
    }

    @Override
    public void cancelAppointment(AppointmentDto appointmentDto) {

    }
}
