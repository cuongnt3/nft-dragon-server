package com.zitga.idle.enumeration.dragon;

public enum EggType {
    COMMON(1),
    UNCOMMON(2),
    RARE(3);

    private final int value;

    EggType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EggType toType(int type) {
        return EggType.values()[type - 1];
    }
}
