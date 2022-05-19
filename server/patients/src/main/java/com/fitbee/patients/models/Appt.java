package com.fitbee.patients.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Appt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private DoctorSlot doctorSlot;
    @OneToOne
    private Patient patient;
}
