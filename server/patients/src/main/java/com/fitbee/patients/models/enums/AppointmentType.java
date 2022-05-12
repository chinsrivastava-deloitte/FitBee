package com.fitbee.patients.models.enums;

public enum AppointmentType {
    REGULAR("regular"),
    CRITICAL("critical");
    private String s;

    AppointmentType(String s) {
        this.s = s;
    }
}
