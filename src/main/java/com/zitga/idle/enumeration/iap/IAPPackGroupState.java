package com.zitga.idle.enumeration.iap;

public enum IAPPackGroupState {
    INACTIVE(1),
    ACTIVE(2),
    SKIPPED(3),
    ;

    private final int value;

    IAPPackGroupState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static IAPPackGroupState toGroupState(int state) {
        return IAPPackGroupState.values()[state - 1];
    }
}
