package com.fitbee.patients.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class Research {

    @Lob
  //  @Column(name = "Description")
    private String description;
   // @Column(name = "Research_Start_Date")
    @Temporal(TemporalType.DATE)
    private Date researchStartDate;
   // @Temporal(TemporalType.DATE)
    @Column(name = "Research_End_Date")
    private Date researchEndDate;

}
