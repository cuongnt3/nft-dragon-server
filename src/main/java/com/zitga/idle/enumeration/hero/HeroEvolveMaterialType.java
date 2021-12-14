package com.zitga.idle.enumeration.hero;

public enum HeroEvolveMaterialType {
    RANDOM_HERO(0),
    SAME_FACTION(1),
    SAME_HERO(2),
    SPECIFIC_HERO(3);

    private final int value;

    HeroEvolveMaterialType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroEvolveMaterialType toMaterialType(int type) {
        return HeroEvolveMaterialType.values()[type];
    }
}
