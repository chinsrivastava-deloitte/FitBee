package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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
    private int experience;
    private String expertise;
    private Boolean isVerified;
    @Embedded
    private Qualification qualification;
    @Embedded
    private Research research;

}

