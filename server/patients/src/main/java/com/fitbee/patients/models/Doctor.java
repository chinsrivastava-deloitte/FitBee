package com.fitbee.patients.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "Doctor_ID")
    private int doctorId;
   // @Column(name = "Name")
    private String name;
   // @Column(name = "Years_of_Experience")
    private int experience;
   // @Column(name = "Specialisation")
    private String expertise;
    //@Column(name = "Verified")
    private Boolean isVerified;
    @Embedded
    private Qualification qualification;
    @Embedded
    private Research research;
    @OneToOne
    private User user;
    //@JsonManagedReference

    @OneToMany(mappedBy = "doctor",targetEntity = Appointment.class)
    private List<Appointment> appointments;

}

