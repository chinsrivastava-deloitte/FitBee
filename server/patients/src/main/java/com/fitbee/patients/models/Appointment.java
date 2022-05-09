package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    private long appointmentId;
    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    private Date date;
    @Temporal(TemporalType.TIME)
    @Column(name = "Start_Time")
    private Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "End_Time")
    private Date endTime;

}
