package com.zitga.idle.enumeration.tracking.hero;

public enum HeroTrackingState {
    ACQUIRE(0),
    REMOVE(1),
    LEVEL_UP(2),
    EVOLVE(3),
    USE_AS_FOOD(4),
    CONVERT_ADD(5),
    CONVERT_REMOVE(6),
    ACQUIRE_FRAGMENT(7),
    REMOVE_FRAGMENT(8),
    ;

    private final int value;

    HeroTrackingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroTrackingState toTrackingState(int state) {
        return HeroTrackingState.values()[state];
    }
}