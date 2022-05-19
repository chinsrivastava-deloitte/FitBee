package com.fitbee.patients.services;

import com.fitbee.patients.exceptions.DateException;
import com.fitbee.patients.exceptions.IdNotFoundException;
import com.fitbee.patients.models.Appointment;
import com.fitbee.patients.models.Appt;
import com.fitbee.patients.models.DoctorSlot;
import com.fitbee.patients.models.Slot;
import com.fitbee.patients.utils.dto.AppointmentDto;
import com.fitbee.patients.utils.dto.PreviousAppointmentDto;
import com.fitbee.patients.utils.dto.RescheduleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    public abstract void addAppointment(AppointmentDto appointmentDto) throws IdNotFoundException, DateException;

    public abstract void addappointment(AppointmentDto appointmentDto);

    public abstract List<Appt> getAppointments();
    public abstract List<Slot> getAllSlots();
    public abstract List<DoctorSlot> getDoctorSlots();
    public abstract List<PreviousAppointmentDto> getPreviousAppointments(int patientId);
    public abstract void rescheduleAppointment(RescheduleDto rescheduleDto);
    public abstract void cancelAppointment(AppointmentDto appointmentDto);
}
// previous appointment
// upcoming appointment