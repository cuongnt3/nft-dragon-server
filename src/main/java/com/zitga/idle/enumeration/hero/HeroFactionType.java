package com.zitga.idle.enumeration.hero;

public enum HeroFactionType {
    WATER(1),
    FIRE(2),
    ABYSS(3),
    NATURE(4),

    LIGHT(5),
    DARK(6),

    CHAOS(7),
    ;

    private final int value;

    HeroFactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroFactionType toFactionType(int factionType) {
        return HeroFactionType.values()[factionType - 1];
    }
}
