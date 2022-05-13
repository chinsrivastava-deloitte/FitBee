package com.fitbee.patients.controllers;

import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.services.AppointmentService;
import com.fitbee.patients.services.PatientService;
import com.fitbee.patients.utils.dto.AppointmentDto;
import com.fitbee.patients.utils.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentService appointmentService;
    @GetMapping(value = "/patients")
    public ResponseEntity<Object> getPatients() {

        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }
    @GetMapping(value="/patients/{id}")
    public ResponseEntity<Object> getPatientByID(@PathVariable("id") int id)  {
        try{
            return new ResponseEntity<>(patientService.getPatientByID(id), HttpStatus.OK);
        }
        catch(IdNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping(value="/patient/{name}")
//    public ResponseEntity<Object> getPatientByID(@PathVariable("name") String name) {
//        return new ResponseEntity<>(patientService.getPatientByName(name), HttpStatus.OK);
//    }


    @PutMapping(value = "/patients/{id}")
    //patientdto = userid, patientdetails
    public ResponseEntity<Object> updatePatient(@PathVariable("id") int id, @RequestBody Patient patient) {
        try{
            patientService.updatePatient(id, patient);
            return new ResponseEntity<>("Patient is updated successfully", HttpStatus.OK);
        }
        catch (IdNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable("id") int id) {
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>("Patient is deleted successfully", HttpStatus.OK);
        }
        catch (IdNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/patients")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
        return new ResponseEntity<>("Patient is created successfully", HttpStatus.CREATED);
    }
    @PostMapping("/createPatients")
    public ResponseEntity<Object> addPatient(@RequestBody PatientDto patientDto){
        patientService.addPatient(patientDto);
        return new ResponseEntity<>("Patient is created successfully", HttpStatus.CREATED);
    }
//    @PostMapping(value="/patients/{name}/appointment")
//    public ResponseEntity<Object> scheduleAppointment(@PathVariable("name") String name,@RequestBody Appointment appointment){
//        appointmentService.addAppointment(appointment,name);
//        return new ResponseEntity<>("appointment added",HttpStatus.CREATED);
//    }

    @GetMapping("/caseHistory/{patientName}")
    public ResponseEntity<Object> getCaseHistory(@PathVariable("patientName") String patientName){
        return new ResponseEntity<>(patientService.getUserCaseHistory(patientName),HttpStatus.OK);
    }
    @PostMapping("/addAppointments")
    public ResponseEntity<Object> addAppointment(@RequestBody AppointmentDto appointmentDto){
        try{
            appointmentService.addAppointment(appointmentDto);
            return new ResponseEntity<>("appointment added",HttpStatus.CREATED);
        }
        catch(IdNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch(DateException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getAppointments")
    public ResponseEntity<Object> getAppointments(){
        return new ResponseEntity<>(appointmentService.getAppointments(),HttpStatus.OK);
    }
    @GetMapping("/patientsByUser/{userId}")
    public ResponseEntity<Object> getPatientByUserId(@PathVariable int userId){
        return new ResponseEntity<>(patientService.fetchPatientByUser(userId),HttpStatus.OK);
    }
}
