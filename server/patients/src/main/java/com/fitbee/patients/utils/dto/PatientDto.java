package com.fitbee.patients.utils.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {
    private int userId;
    private String first_name;
    private String last_name;
    private String address;
    private String gender;
    private float height;
    private float weight;
}
