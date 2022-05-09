package com.fitbee.patients.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class Research {

    @Lob
    private String description;
    @Temporal(TemporalType.DATE)
    private Date researchStartDate;
    @Temporal(TemporalType.DATE)
    private Date researchEndDate;

}
