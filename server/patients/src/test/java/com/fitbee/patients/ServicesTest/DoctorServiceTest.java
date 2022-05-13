package com.fitbee.patients.ServicesTest;

import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Qualification;
import com.fitbee.patients.models.Research;
import com.fitbee.patients.repositories.DoctorRepository;
import com.fitbee.patients.services.DoctorService;
import com.fitbee.patients.services.DoctorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DoctorServiceTest {

    @Autowired
    DoctorServiceImpl doctorService;

    @MockBean
    DoctorRepository doctorRepository;

    @Test
    public void createDoctorTest(){
        Qualification qualification=new Qualification();
        qualification.setCollege("St. Xaviers");
        qualification.setDegree("Biology");
        Research research =new Research();
        Doctor doctor= new Doctor(1,"doctor1",12,"medicine",false,qualification,research,null,new ArrayList<Appointment>());

        doctorService.createDoctor(doctor);
        verify(doctorRepository).save(doctor);
    }

    @Test
    public void deleteDoctorTest(){
        Qualification qualification=new Qualification();
        Research research = new Research();
        Doctor doctor= new Doctor(1,"doctor1",12,"medicine",false,qualification,research,null,new ArrayList<Appointment>());

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        doctorRepository.deleteById(1);
        verify(doctorRepository).deleteById(1);
    }

}
