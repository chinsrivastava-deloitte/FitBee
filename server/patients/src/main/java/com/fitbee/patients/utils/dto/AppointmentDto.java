package com.fitbee.patients.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Getter
@Setter
public class AppointmentDto {
    private int doctorId;
    private int patientId;
    /*@Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;*/
    private int slotId;
    private String type;
}
