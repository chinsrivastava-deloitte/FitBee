package com.fitbee.patients.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitbee.patients.models.enums.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
public class DoctorSlot {
    @Id
    @GeneratedValue
    private int id;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Slot slot;
    private SlotStatus isOccupied;
}
