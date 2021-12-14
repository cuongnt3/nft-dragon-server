package com.zitga.idle.enumeration.event.eventEaster;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EventEasterEggType {
    SILVER(1),
    YELLOW(2),
    RAINBOW(3),
    ;

    private static final Map<Integer, EventEasterEggType> eggTypeMap = new ConcurrentHashMap<>();
    private final int value;

    EventEasterEggType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EventEasterEggType toType(int type) {
        return eggTypeMap.get(type);
    }

    static {
        EventEasterEggType[] eggTypes = EventEasterEggType.values();
        for (EventEasterEggType eggType : eggTypes) {
            eggTypeMap.put(eggType.getValue(), eggType);
        }
    }
}
