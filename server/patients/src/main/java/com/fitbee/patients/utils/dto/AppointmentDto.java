package com.fitbee.patients.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AppointmentDto {
    private int doctorId;
    private int patientId;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String type;
}
