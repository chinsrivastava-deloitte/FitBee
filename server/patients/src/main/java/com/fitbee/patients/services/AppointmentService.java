package com.fitbee.patients.services;

import com.fitbee.patients.models.Appointment;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {
    public abstract void addAppointment(Appointment appointment,String name);
}
