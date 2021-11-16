package com.zitga.enumeration.hatch;

import com.zitga.enumeration.observer.ListenerPriority;

public enum HatchType {
    BASIC(0),
    NORMAL(1),
    PREMIUM(2);

    private final int value;

    HatchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HatchType toType(int type) {
        return HatchType.values()[type];
    }
}
