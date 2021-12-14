package com.zitga.idle.enumeration.raiseHero;

public enum RaiseHeroFeatureState {
    LOCKED(1),
    ACTIVE(2),
    INACTIVE(3),
    ;

    private final int value;

    RaiseHeroFeatureState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RaiseHeroFeatureState toState(int state) {
        return RaiseHeroFeatureState.values()[state - 1];
    }
}
