package com.zitga.idle.enumeration.feature;

public enum FeatureState {
    LOCK(1),
    COMING_SOON(2),
    UNLOCK(3),
    MAINTAIN(4),
    ;

    private final int value;

    FeatureState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FeatureState toType(int state) {
        if (state >= 1 && state <= FeatureState.values().length) {
            return FeatureState.values()[state - 1];
        }

        return null;
    }
}
