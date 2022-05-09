package com.fitbee.patients.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 *This class is not reflected in the database
 * This is the composite attribute for doctor qualification
 */
@Getter
@Setter
@Embeddable
public class Qualification{

    private String college;
    private String degree;
    private Date educationStartDate;
    private Date educationEndDate;

}
