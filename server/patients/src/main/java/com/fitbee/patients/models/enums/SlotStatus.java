package com.fitbee.patients.models.enums;

public enum SlotStatus {
    FREE(0),
    OCCUPIED(1),
    BLOCKED(-1);
    private int i;

    SlotStatus(int i) {
        this.i=i;

    }
}
