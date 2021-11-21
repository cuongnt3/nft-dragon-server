package com.zitga.enumeration.dragon;

public enum DragonFactionType {
    METAL(1),
    WATER(2),
    WOOD(3),
    FIRE(4),
    EARTH(5),
    LIGHT(6),
    DARK(7);

    private final int value;

    DragonFactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DragonFactionType toType(int type) {
        return DragonFactionType.values()[type - 1];
    }
}
