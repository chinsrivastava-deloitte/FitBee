package com.fitbee.patients.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
   // @Column(name = "University")
    private String college;
    //@Column(name = "Degree")
    private String degree;
   // @Column(name = "Start_Date")
    private Date educationStartDate;
  //  @Column(name = "End_Date")
    private Date educationEndDate;

}
