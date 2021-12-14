package com.zitga.idle.enumeration.hero;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HeroEvolveFoodMaterialType {
    MOON(0),
    SUN(1),

    MOON_WATER(11),
    MOON_FIRE(12),
    MOON_ABYSS(13),
    MOON_NATURE(14),
    SUN_LIGHT(15),
    SUN_DARK(16),
    ;

    private static final Map<Integer, HeroEvolveFoodMaterialType> typeMap = new ConcurrentHashMap<>();
    private final int value;

    HeroEvolveFoodMaterialType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HeroEvolveFoodMaterialType toMaterialType(int type) {
        return typeMap.get(type);
    }

    static {
        HeroEvolveFoodMaterialType[] foodMaterialTypes = HeroEvolveFoodMaterialType.values();
        for (HeroEvolveFoodMaterialType type : foodMaterialTypes) {
            typeMap.put(type.getValue(), type);
        }
    }
}
