package com.fitbee.patients.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.models.User;
import com.fitbee.patients.repositories.PatientRepository;
import com.fitbee.patients.services.PatientServiceImpl;
import com.fitbee.patients.services.SeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PatientServiceTest {

   // List<Patient>patients = new ArrayList<>();
    @Autowired
    PatientServiceImpl patientServiceImpl;

    @MockBean
    PatientRepository patientRepository;

    @BeforeEach
    public void initializeFields(){
        //patients = seedService
    }
    @Test
    public void getAllPatientsTest(){

        List<Patient>patients =new ArrayList<>();
        Appointment appointment= new Appointment();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        User user = new User();
        Patient patientOne= new Patient(1,"John","Ray","Hyderabad","Male",0,null,new ArrayList<Appointment>());
        Patient patientTwo =new Patient(2,"Mary","Jim","Pune,Maharastra","Female",0,user,appointments);
        Patient patientThree= new Patient(3,"Ravi","Singh","New Delhi","Male",0,user,appointments);

        patients.add(patientOne);
        patients.add(patientTwo);
        patients.add(patientThree);

        when(patientRepository.findAll()).thenReturn(patients);
        //doReturn(patients).when(patientRepository.findAll());
        List<Patient>patientList=patientServiceImpl.getPatients();

        assertEquals(3,patientList.size());


    }

   /*@Test
    public void getOnePatientTest()
    {
        when(patientRepository.getById(1)).thenReturn(new Patient(1,"John","Ray","Hyderabad","Male",0,null,new ArrayList<Appointment>()));

        Patient patient = patientServiceImpl.getPatientByID(1);

        assertEquals("John", patient.getFirstName());
        assertEquals("Gupta", patient.getLastName());
        assertEquals("Hyderabad", patient.getAddress());
        assertEquals("Male",patient.getGender());
    }*/
}
