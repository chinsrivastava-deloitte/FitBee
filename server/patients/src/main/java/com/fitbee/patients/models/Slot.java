package com.fitbee.patients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int slotId;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date toTime;
    public Slot(Date date,Date fromTime,Date toTime){
        this.date=date;
        this.fromTime=fromTime;
        this.toTime = toTime;
    }
}
