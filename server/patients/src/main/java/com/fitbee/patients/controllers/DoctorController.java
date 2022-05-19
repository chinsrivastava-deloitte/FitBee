package com.fitbee.patients.controllers;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.services.DoctorService;
import com.fitbee.patients.utils.dto.PrescriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value="*")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping(value = "/doctors")
    public ResponseEntity<Object> getDoctors() {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
    }
    @GetMapping(value="/doctors/{id}")
    public ResponseEntity<Object> getDoctorByID(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(doctorService.getDoctorByID(id), HttpStatus.OK);
        }
        catch(IdNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
//    @GetMapping(value="/doctor/{name}")
//    public ResponseEntity<Object> getDoctorByName(@PathVariable("name") String name){
//        return new ResponseEntity<>(doctorService.getDoctorByName(name),HttpStatus.CREATED);
//    }
    @PutMapping(value = "/doctors/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor) {
        try{
            doctorService.updateDoctor(id,doctor);
            return new ResponseEntity<>("Doctor is updated successfully", HttpStatus.OK);
        }
        catch (IdNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping(value = "/doctors/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable("id") int id) {
        try{
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>("Doctor is deleted successfully", HttpStatus.OK);
        }
        catch(IdNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(value = "/doctors")
    public ResponseEntity<Object> createProduct(@RequestBody Doctor doctor) {
        doctorService.createDoctor(doctor);
        return new ResponseEntity<>("Doctor is created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/Prescribe")
    public ResponseEntity<Object>createPrescription(@RequestBody PrescriptionDto prescriptionDto){
        doctorService.addPrescription(prescriptionDto);
        return new ResponseEntity<Object>("successfully added prescription",HttpStatus.OK);
    }
    @GetMapping("/patientCheckout/{appointmentId}")
    public ResponseEntity<Object> checkoutPatient(@PathVariable int appointmentId){
        doctorService.appointmentCheckout(appointmentId);
        return new ResponseEntity<Object>("successfully checked out patient",HttpStatus.OK);
    }

}
