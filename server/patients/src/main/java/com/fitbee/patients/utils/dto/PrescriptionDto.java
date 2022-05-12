package com.fitbee.patients.utils.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionDto {

    private String diagnosis;
    private String prescription;
    private int appointmentId;
}
