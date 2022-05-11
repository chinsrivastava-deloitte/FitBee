package com.fitbee.patients.services;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Collection;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(int id, Doctor doctor) {
        Doctor oldDoctor = doctorRepository.findById(id).get();
        oldDoctor.setExperience(doctor.getExperience());
        oldDoctor.setExpertise(doctor.getExpertise());
        oldDoctor.setName(doctor.getName());
        oldDoctor.setIsVerified(doctor.getIsVerified());
        oldDoctor.setResearch(doctor.getResearch());
        oldDoctor.setQualification(doctor.getQualification());
        doctorRepository.save(oldDoctor);

    }

    @Override
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Collection<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorByID(int id) {
            return doctorRepository.findById(id).get();
    }
//    @Override
//    public Doctor getDoctorByName(String name){
//        return doctorRepository.findByName(name);
//    }
}
