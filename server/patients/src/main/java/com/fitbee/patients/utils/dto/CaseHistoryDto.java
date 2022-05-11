package com.fitbee.patients.utils.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CaseHistoryDto {

    private Date date;
    private String doctorName;
    private String diagnosis;
    private String prescription;

}
