package com.zitga.enumeration.dragon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EggFragmentType {
    COMMON(1),

    METAL(11),
    WATER(12),
    WOOD(13),
    FIRE(14),
    EARTH(15),
    LIGHT(16),
    DARK(17);

    private static final Map<Integer, EggFragmentType> eggFragments = new ConcurrentHashMap<>();
    private final int value;

    EggFragmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EggFragmentType toType(int type) {
        return eggFragments.get(type);
    }

    static {
        for (EggFragmentType eggFragmentType : EggFragmentType.values()) {
            eggFragments.put(eggFragmentType.getValue(), eggFragmentType);
        }
    }
}
