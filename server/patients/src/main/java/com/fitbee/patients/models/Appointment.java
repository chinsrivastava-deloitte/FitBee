package com.fitbee.patients.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="appointment")
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
    @Column(name="Diagnosis")
    private String diagnosis;
    @Column(name = "Prescription")
    private String prescription;
    @Column(name="Doctor_name")
    private String doctorName;
    // @JsonBackReference

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
   // @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

}
