package com.zitga.idle.enumeration.summon;

public enum SummonType {
    SUMMON_BASIC(0),
    SUMMON_PREMIUM(1),
    SUMMON_FRIEND(2),
    SUMMON_POINT(3),
    SUMMON_FRAGMENT(4),
    PROPHET_TREE_SUMMON(5),
    EVENT_RATE_UP(6),
    EVENT_NEW_HERO_SUMMON(7),
    EVENT_NEW_HERO_RATE_UP(8),
    EVENT_HERO_RELEASE_RATE_UP(9),
    ;

    private final int value;

    SummonType(int value) {
        this.value = value;
    }

    public static SummonType toSummonType(int summonType) {
        return SummonType.values()[summonType];
    }

    public int getValue() {
        return this.value;
    }
}
