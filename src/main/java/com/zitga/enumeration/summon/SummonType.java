package com.zitga.enumeration.summon;

public enum SummonType {
    BASIC(0),
    NORMAL(1),
    PREMIUM(2);

    private final int value;

    SummonType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SummonType toType(int type) {
        return SummonType.values()[type];
    }
}
