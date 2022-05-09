package com.fitbee.patients.controllers;

import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping(value = "/doctors")
    public ResponseEntity<Object> getDoctors() {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
    }
    @GetMapping(value="/doctors/{id}")
    public ResponseEntity<Object> getDoctorByID(@PathVariable("id") int id) {
        return new ResponseEntity<>(doctorService.getDoctorByID(id), HttpStatus.OK);
    }

    @PutMapping(value = "/doctors/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor) {
        doctorService.updateDoctor(id,doctor);
        return new ResponseEntity<>("Doctor is updated successfully", HttpStatus.OK);
    }
    @DeleteMapping(value = "/doctors/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable("id") int id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Doctor is deleted successfully", HttpStatus.OK);
    }
    @PostMapping(value = "/doctors")
    public ResponseEntity<Object> createProduct(@RequestBody Doctor doctor) {
        doctorService.createDoctor(doctor);
        return new ResponseEntity<>("Doctor is created successfully", HttpStatus.CREATED);
    }

}
