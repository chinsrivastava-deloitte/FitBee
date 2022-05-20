package com.fitbee.patients.utils.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class RescheduleDto {
    private int appointmentId;
    private Date date;
    private Date startTime;
    private Date endTime;
    private int slotId;
}
