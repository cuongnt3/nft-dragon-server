package com.zitga.idle.enumeration.raiseHero;

public enum RaisedSlotState {
    LOCKED(1),
    UNLOCKED(2),
    USED(3),
    COUNTDOWN(4);

    private final int value;

    RaisedSlotState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RaisedSlotState toRaisedSlotState(int state) {
        return RaisedSlotState.values()[state - 1];
    }
}
