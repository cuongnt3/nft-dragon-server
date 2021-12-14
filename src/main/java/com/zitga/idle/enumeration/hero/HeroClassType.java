package com.zitga.idle.enumeration.hero;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HeroClassType {
    MAGE(1),
    WARRIOR(2),
    PRIEST(3),
    ASSASSIN(4),
    RANGER(5),
    ;

    private static final Map<Integer, HeroClassType> classTypeMap = new ConcurrentHashMap<>();
    private final int value;

    HeroClassType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroClassType toHeroClassType(int type) {
        return classTypeMap.get(type);
    }

    static {
        HeroClassType[] heroClassTypes = HeroClassType.values();
        for (HeroClassType classType : heroClassTypes) {
            classTypeMap.put(classType.getValue(), classType);
        }
    }
}
