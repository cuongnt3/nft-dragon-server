package com.zitga.idle.enumeration.defenseMode;

public enum DefenseRestrictType {
    WATER(1),
    FIRE(2),
    ABYSS(3),
    NATURE(4),
    LIGHT(5),
    DARK(6),
    DREAM(7),
    CHAOS(8),
    UNBORN(9),
    LIBRARY(10),
    ;

    private final int value;

    DefenseRestrictType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DefenseRestrictType toDefenseModeRestrictType(int type) {
        return DefenseRestrictType.values()[type - 1];
    }
}
