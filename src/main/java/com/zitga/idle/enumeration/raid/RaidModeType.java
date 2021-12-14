package com.zitga.idle.enumeration.raid;

public enum RaidModeType {
    GOLD(0),
    MAGIC_POTION(1),
    HERO_FRAGMENT(2);

    private final int value;

    RaidModeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RaidModeType toRaidModeType(int type) {
        return RaidModeType.values()[type];
    }
}