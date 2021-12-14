package com.zitga.idle.enumeration.tracking.resource;

public enum ResourceTrackingState {
    RESOURCE_SPEND(0),
    RESOURCE_EARN(1),
    RESOURCE_BUY(2),
    ;

    private final int value;

    ResourceTrackingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ResourceTrackingState toTrackingState(int state) {
        return ResourceTrackingState.values()[state];
    }
}