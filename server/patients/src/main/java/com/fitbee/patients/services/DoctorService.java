package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Doctor;
import com.fitbee.patients.models.Slot;
import com.fitbee.patients.utils.dto.AppointmentSlots;
import com.fitbee.patients.utils.dto.PrescriptionDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    public abstract void createDoctor(Doctor doctor);
    public abstract void updateDoctor(int id, Doctor doctor) throws IdNotFoundException;
    public abstract void deleteDoctor(int id) throws IdNotFoundException;
    public abstract Collection<Doctor> getDoctors();
    public abstract Doctor getDoctorByID(int id) throws IdNotFoundException;
    //public abstract Doctor getDoctorByName(String name);
    public abstract void addPrescription(PrescriptionDto prescriptionDto);
    public abstract void appointmentCheckout(int appointmentId);
    public abstract void setPatientStatus(int appointmentId,String status);
    public abstract List<AppointmentSlots> getDoctorSlot(int doctorId);
}
