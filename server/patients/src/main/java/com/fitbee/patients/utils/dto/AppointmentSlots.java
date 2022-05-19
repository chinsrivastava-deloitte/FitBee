package com.fitbee.patients.utils.dto;

import com.fitbee.patients.models.Slot;
import com.fitbee.patients.models.enums.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSlots {

    private Slot slot;
    private SlotStatus occupied;

}
