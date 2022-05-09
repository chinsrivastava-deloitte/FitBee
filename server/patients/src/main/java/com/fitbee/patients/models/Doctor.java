package com.fitbee.patients.models;

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
    @OneToMany(targetEntity = Appointment.class)
    private List<Appointment> appointments;

}

