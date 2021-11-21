package com.zitga.enumeration.random;

public enum RandomType {

    COMMON(1),

    SUMMON_BASIC_EGG(2),
    SUMMON_NORMAL_EGG(3),
    SUMMON_RARE_EGG(4);

    private final int value;

    RandomType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
