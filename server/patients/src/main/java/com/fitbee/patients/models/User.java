package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long userId;
    private String userName;
    private String password;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private int phoneNumber;
    private String role;


}
