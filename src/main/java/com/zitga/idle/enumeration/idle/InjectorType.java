package com.zitga.idle.enumeration.idle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum InjectorType {

    // feature injector type
    // value = feature type
    RAISED_HERO(20),

    // event injector type
    // value = 1000 * event type
    EVENT_MID_AUTUMN(1024),
    EVENT_ARENA_PASS(1027),
    EVENT_DAILY_QUEST_PASS(1028),
    EVENT_HALLOWEEN(1029),
    EVENT_CHRISTMAS(1030),
    EVENT_NEW_YEAR(1031),
    EVENT_LUNAR_NEW_YEAR(1032),
    EVENT_EASTER(1042),
    EVENT_NEW_HERO_TREASURE(1046),
    EVENT_BIRTHDAY(1055),
    EVENT_HERO_RELEASE(1057),
    ;

    private static final Map<Integer, InjectorType> injectorTypeMap = new ConcurrentHashMap<>();
    private final int value;

    InjectorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InjectorType toType(int type) {
        return injectorTypeMap.get(type);
    }

    static {
        InjectorType[] injectorTypes = InjectorType.values();
        for (InjectorType injectorType : injectorTypes) {
            injectorTypeMap.put(injectorType.getValue(), injectorType);
        }
    }
}
