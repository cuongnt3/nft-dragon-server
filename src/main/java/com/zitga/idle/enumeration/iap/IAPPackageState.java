package com.zitga.idle.enumeration.iap;

public enum IAPPackageState {
    INACTIVE(1),
    PURCHASED(2),
    SKIPPED(3),
    ;

    private final int value;

    IAPPackageState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static IAPPackageState toPackageState(int state) {
        return IAPPackageState.values()[state - 1];
    }
}
