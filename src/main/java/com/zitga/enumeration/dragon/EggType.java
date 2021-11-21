package com.zitga.enumeration.dragon;

public enum EggType {
    COMMON(0),
    UNCOMMON(1),
    RARE(2);

    private final int value;

    EggType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EggType toType(int type) {
        return EggType.values()[type];
    }
}
