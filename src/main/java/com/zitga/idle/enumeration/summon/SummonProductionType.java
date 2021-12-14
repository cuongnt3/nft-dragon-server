package com.zitga.idle.enumeration.summon;

public enum SummonProductionType {
    BASIC_SCROLL(0),
    PREMIUM_SCROLL(1),
    FRIEND_POINT(2),
    SUMMON_POINT(3),
    EVENT_RATE_UP(4),

    PROPHET_TREE_WATER_SUMMON(5),
    PROPHET_TREE_FIRE_SUMMON(6),
    PROPHET_TREE_ABYSS_SUMMON(7),
    PROPHET_TREE_NATURE_SUMMON(8),
    PROPHET_TREE_LIGHT_SUMMON(9),
    PROPHET_TREE_DARK_SUMMON(10),

    PROPHET_TREE_WATER_CONVERT(11),
    PROPHET_TREE_FIRE_CONVERT(12),
    PROPHET_TREE_ABYSS_CONVERT(13),
    PROPHET_TREE_NATURE_CONVERT(14),
    PROPHET_TREE_LIGHT_CONVERT(15),
    PROPHET_TREE_DARK_CONVERT(16),

    FRAGMENT_WATER_SUMMON(17),
    FRAGMENT_FIRE_SUMMON(18),
    FRAGMENT_ABYSS_SUMMON(19),
    FRAGMENT_NATURE_SUMMON(20),
    FRAGMENT_LIGHT_SUMMON(21),
    FRAGMENT_DARK_SUMMON(22),

    EVENT_RATE_UP_SUMMON_POINT(23),
    EVENT_NEW_HERO_SUMMON(24),
    EVENT_NEW_HERO_RATE_UP(25),

    EVENT_HERO_RELEASE_RATE_UP(26)
    ;

    private final int value;

    SummonProductionType(int value) {
        this.value = value;
    }

    public static SummonProductionType toProductionType(int type) {
        return SummonProductionType.values()[type];
    }

    public int getValue() {
        return this.value;
    }
}
