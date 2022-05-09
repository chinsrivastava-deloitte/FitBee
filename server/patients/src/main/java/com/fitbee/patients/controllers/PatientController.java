package com.fitbee.patients.controllers;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Patient;
import com.fitbee.patients.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping(value = "/patients")
    public ResponseEntity<Object> getPatients() {
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }
    @GetMapping(value="/patients/{id}")
    public ResponseEntity<Object> getPatientByID(@PathVariable("id") int id) {
        return new ResponseEntity<>(patientService.getPatientByID(id), HttpStatus.OK);
    }

    @PutMapping(value = "/patients/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable("id") int id, @RequestBody Patient patient) {
        patientService.updatePatient(id, patient);
        return new ResponseEntity<>("Patient is updated successfully", HttpStatus.OK);
    }
    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable("id") int id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient is deleted successfully", HttpStatus.OK);
    }
    @PostMapping(value = "/patients")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
        return new ResponseEntity<>("Patient is created successfully", HttpStatus.CREATED);
    }
}
