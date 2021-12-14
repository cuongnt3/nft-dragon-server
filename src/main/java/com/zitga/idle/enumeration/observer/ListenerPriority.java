package com.zitga.idle.enumeration.observer;

public enum ListenerPriority {
    LOWEST(6),
    LOWER(5),
    LOW(4),
    DEFAULT(3),
    HIGH(2),
    HIGHER(1),
    HIGHEST(0),
    ;

    private final int value;

    ListenerPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ListenerPriority toListenerPriority(int type) {
        if (type >= 0 && type < ListenerPriority.values().length) {
            return ListenerPriority.values()[type];
        }

        return null;
    }
}