package com.zitga.idle.enumeration.casino;

public enum CasinoType {
    BASIC(0),
    PREMIUM(1),
    ;

    private final int value;

    CasinoType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static CasinoType toCasinoType(int casinoType) {
        return CasinoType.values()[casinoType];
    }
}
