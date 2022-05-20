package com.fitbee.patients.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Address")
    private String address;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Bed_ID")
    private int bedId;

    @OneToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name="user_id")
    private User user;
    //@JsonManagedReference
    //@JsonIgnore
    @OneToMany(mappedBy = "patient",targetEntity = Appointment.class)
    private List<Appointment> appointments;
    @Column(name="height")
    private float height;
    @Column(name="weight")
    private float weight;




//    @OneToMany
//    @JoinColumn()
//    private List<Appointment> appointments;
}
