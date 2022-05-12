package com.fitbee.patients.models.enums;

public enum AppointmentEnum {

    PENDING(0),
    NOT_STARTED(-1),
    COMPLETED(1);
    private int i;
    AppointmentEnum(int i) {
                this.i=i;
    }
}
