package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.models.User;
import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.repositories.AppointmentRepository;
import com.fitbee.patients.repositories.PatientRepository;
import com.fitbee.patients.repositories.UserRepository;
import com.fitbee.patients.utils.dto.CaseHistoryDto;
import com.fitbee.patients.utils.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        caseHistoryDto.setDate(appointment.getDate());
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


}
