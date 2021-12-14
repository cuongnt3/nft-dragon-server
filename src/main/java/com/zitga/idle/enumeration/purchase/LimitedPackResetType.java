package com.zitga.idle.enumeration.purchase;

public enum LimitedPackResetType {
    RESET_BY_WEEK(0),
    RESET_BY_MONTH(1),
    RESET_BY_DAY(2),
    ;

    private final int value;

    LimitedPackResetType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static LimitedPackResetType toType(int type) {
        return LimitedPackResetType.values()[type];
    }
}