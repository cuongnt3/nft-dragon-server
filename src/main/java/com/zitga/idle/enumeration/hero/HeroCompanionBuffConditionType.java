package com.zitga.idle.enumeration.hero;

public enum HeroCompanionBuffConditionType {
    SAME_FACTION(1),
    DIFFERENT_FACTION(2),
    ;

    private final int value;

    HeroCompanionBuffConditionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroCompanionBuffConditionType toConditionType(int conditionType) {
        return HeroCompanionBuffConditionType.values()[conditionType - 1];
    }
}
