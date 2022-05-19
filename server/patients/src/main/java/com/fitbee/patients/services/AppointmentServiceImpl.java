package com.fitbee.patients.services;

import com.fitbee.patients.config.EmailConfig;
import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.*;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.AppointmentType;
import com.fitbee.patients.models.enums.SlotStatus;
import com.fitbee.patients.repositories.*;
import com.fitbee.patients.utils.dto.AppointmentDto;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import com.fitbee.patients.utils.dto.EmailDto;
import com.fitbee.patients.utils.dto.PreviousAppointmentDto;
import com.fitbee.patients.utils.dto.RescheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    @Autowired
    EmailConfig emailConfig;
    @Autowired
    JavaMailSender javaMailSender;



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
        appointment.setDescription(appointmentDto.getType());
        appointmentRepository.save(appointment);
        sendMail(appointmentDto);
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
                //previousAppointmentDto.setDescription(a.getAppointmentType());
                previousAppointmentDto.setDescription(a.getDescription());
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

    public List<PreviousAppointmentDto>getAllAppointmentsDto(int patientId){
        List<Appointment> appointmentList = patientRepository.findById(patientId).get().getAppointments();
        List<PreviousAppointmentDto> previousAppointmentList = new ArrayList<>();
        for (Appointment a : appointmentList) {
            PreviousAppointmentDto previousAppointmentDto = new PreviousAppointmentDto();
            previousAppointmentDto.setDoctorName(a.getDoctor().getName());
            previousAppointmentDto.setDate(a.getDate());
            previousAppointmentDto.setTime(a.getStartTime());
            previousAppointmentDto.setDescription(a.getDescription());
            previousAppointmentList.add(previousAppointmentDto);

        }
        return previousAppointmentList;
    }

    public void sendMail(AppointmentDto appointmentDto){
        int patientId= appointmentDto.getPatientId();
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(patientRepository.findById(patientId).get().getUser().getEmail());
        emailDto.setName(patientRepository.findById(patientId).get().getUser().getUserName());
        emailDto.setMessageBody("This is to confirm that your appointment have been scheduled at"+appointmentDto.getDate()+"from"+appointmentDto.getStartTime()+"with"
                                +doctorRepository.findById(appointmentDto.getDoctorId()).get().getName());
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailDto.getEmail());
        mailMessage.setSubject("Fitbee Appointment");
        mailMessage.setText(emailDto.getMessageBody());
        mailSender.send(mailMessage);
    }
}
