package com.fitbee.patients.utils.dto;

import com.fitbee.patients.models.enums.AppointmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionDto {

    private String diagnosis;
    private String prescription;
    private int appointmentId;
    private AppointmentType appointmentType;
}
