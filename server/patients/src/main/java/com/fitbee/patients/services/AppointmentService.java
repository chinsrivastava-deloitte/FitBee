package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.utils.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    public abstract void addAppointment(AppointmentDto appointmentDto) throws IdNotFoundException, DateException;
    public abstract List<Appointment> getAppointments();
}
