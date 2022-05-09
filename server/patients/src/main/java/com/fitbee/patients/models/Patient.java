package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Patient_ID")
    private int patientId;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Address")
    private String address;
    @Column(name = "Gender")
    private String gender;
    private int bedId;
    @Column(name = "Bed_ID")
    private int bedId;

    @OneToOne
    private User user;

    @OneToMany(targetEntity = Appointment.class)
    private List<Appointment> appointments;
}
