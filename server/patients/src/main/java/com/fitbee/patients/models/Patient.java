package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Patient {

    @Id
    private long patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private int bedId;

    @OneToOne
    private User user;

    @OneToMany(targetEntity = Appointment.class)
    private List<Appointment> appointments;
}
