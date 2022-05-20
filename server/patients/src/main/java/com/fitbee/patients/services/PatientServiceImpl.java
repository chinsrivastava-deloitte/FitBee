package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.*;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.SlotStatus;
import com.fitbee.patients.repositories.*;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import com.fitbee.patients.utils.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    SlotRepository slotRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorSlotRepository doctorSlotRepository;

    @Override
    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(PatientDto patientDto)  {
            int userId = patientDto.getUserId();
            int patientID = patientRepository.findByUserUserId((long)userId).getPatientId();
            Patient patient = patientRepository.findById(patientID).get();
            patient.setGender(patientDto.getGender());
            patient.setFirstName(patientDto.getFirst_name());
            patient.setLastName(patientDto.getLast_name());
            patient.setHeight(patientDto.getHeight());
            patient.setWeight(patientDto.getWeight());
            patient.setAddress(patientDto.getAddress());
            patientRepository.save(patient);

    }
//    public boolean check(User user){
//        int id = (int) user.getUserId();
//        if(patientRepository.findByUser(user).getUser().getUserId()
//    }

    @Override
    public void deletePatient(Integer id) throws IdNotFoundException {
        if(id==null)
            throw new IdNotFoundException("id cannot be null");
        if(patientRepository.findById(id).isPresent())
            patientRepository.deleteById(id);
        else
            throw new IdNotFoundException("id not present in database");
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByID(int id) throws IdNotFoundException {
        if(patientRepository.findById(id).isPresent())
            return patientRepository.findById(id).get();
        else
            throw new IdNotFoundException("id not present in database");
    }


    @Override
    public List<CaseHistoryDto> getUserCaseHistory(int patientId){

        List<Appointment>patientAppointments=patientRepository.findById(patientId).get().getAppointments();
        return patientAppointments.stream().filter(appointment->appointment.getAppointmentStatus()== AppointmentEnum.COMPLETED)
                .map(this::convertCaseHistoryDto).collect(Collectors.toList());

    };

    private CaseHistoryDto convertCaseHistoryDto(Appointment appointment){
        CaseHistoryDto caseHistoryDto= new CaseHistoryDto();
        caseHistoryDto.setDoctorName(appointment.getDoctor().getName());
        caseHistoryDto.setDiagnosis(appointment.getDiagnosis());
        caseHistoryDto.setPrescription(appointment.getPrescription());
        caseHistoryDto.setDate(appointment.getDoctorSlot().getSlot().getDate());
        return caseHistoryDto;
    }
    public int fetchPatientByUser(int userId){
        if(patientRepository.existsByUserUserId((long)userId))
            return patientRepository.findByUserUserId((long)userId).getPatientId();
        else
            return -1;
    }

    public void addPatient(PatientDto patientDto){
        Patient patient=new Patient();
        patient.setFirstName(patientDto.getFirst_name());
        patient.setLastName(patientDto.getLast_name());
        patient.setAddress(patientDto.getAddress());
        patient.setGender(patientDto.getGender());
        patient.setHeight(patientDto.getHeight());
        patient.setWeight(patientDto.getWeight());
        patient.setUser(userRepository.findByUserId((long)patientDto.getUserId()));
        patientRepository.save(patient);

    }
    public void populateSlot(){
        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        Date d1,t1,t2;
        List<Slot> slots = new ArrayList<>();
        try{
            d1= df.parse("2022-05-20");
            t1=sdf.parse("2022-05-20 10:00:00");
            t2=sdf.parse("2022-05-20 11:00:00");
            Slot s1 = new Slot(d1,t1,t2);
            slots.add(s1);
            d1= df.parse("2022-05-20");
            t1=sdf.parse("2022-05-20 11:00:00");
            t2=sdf.parse("2022-05-20 12:00:00");
            Slot s2 = new Slot(d1,t1,t2);
            slots.add(s2);
            d1= df.parse("2022-05-20");
            t1=sdf.parse("2022-05-20 12:00:00");
            t2=sdf.parse("2022-05-20 13:00:00");
            Slot s3 = new Slot(d1,t1,t2);
            slots.add(s3);
            d1= df.parse("2022-05-20");
            t1=sdf.parse("2022-05-20 13:00:00");
            t2=sdf.parse("2022-05-20 14:00:00");
            Slot s4 = new Slot(d1,t1,t2);
            slots.add(s4);
            d1= df.parse("2022-05-21");
            t1=sdf.parse("2022-05-21 10:00:00");
            t2=sdf.parse("2022-05-21 11:00:00");
            Slot s5 = new Slot(d1,t1,t2);
            slots.add(s5);
            d1= df.parse("2022-05-21");
            t1=sdf.parse("2022-05-21 11:00:00");
            t2=sdf.parse("2022-05-21 12:00:00");
            Slot s6 = new Slot(d1,t1,t2);
            slots.add(s6);
            d1= df.parse("2022-05-21");
            t1=sdf.parse("2022-05-21 12:00:00");
            t2=sdf.parse("2022-05-21 13:00:00");
            Slot s7 = new Slot(d1,t1,t2);
            slots.add(s7);
            d1= df.parse("2022-05-21");
            t1=sdf.parse("2022-05-21 13:00:00");
            t2=sdf.parse("2022-05-21 14:00:00");
            Slot s8 = new Slot(d1,t1,t2);
            slots.add(s8);


            slotRepository.saveAll(slots);

        }
        catch(ParseException e){
            e.printStackTrace();
        }

    }
    @Override
    public void populateDoctorSlotTable(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<Slot> slots = slotRepository.findAll();
        for(Doctor d:doctors){
            for (Slot s:slots){
                DoctorSlot ds = new DoctorSlot();
                ds.setIsOccupied(SlotStatus.FREE);
                ds.setDoctor(d);
                ds.setSlot(s);
                doctorSlotRepository.save(ds);
            }
        }
    }
}
