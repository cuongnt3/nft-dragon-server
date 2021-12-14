package com.zitga.idle.enumeration.tracking.item;

public enum ItemTrackingState {
    ACQUIRE(0),
    REMOVE(1),
    UPGRADE(2),
    ;

    private final int value;

    ItemTrackingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ItemTrackingState toTrackingState(int state) {
        return ItemTrackingState.values()[state];
    }
}
