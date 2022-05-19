package com.fitbee.patients.utils.dto;

import com.fitbee.patients.models.enums.AppointmentEnum;
import com.fitbee.patients.models.enums.AppointmentType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PreviousAppointmentDto {
    private String doctorName;
    private Date date;
    private Date time;
   // private AppointmentType description;
    private String description;
    private AppointmentEnum appointmentStatus;
}
