package com.zitga.enumeration.fake;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum FakePlayerDataType {
    ADD_DRAGON(0),
    ADD_EGG(1),
    ADD_EGG_FRAGMENT(2),
    RESOURCE(3),
    ;

    private static final Map<Integer, FakePlayerDataType> fakePlayerDataTypeMap = new ConcurrentHashMap<>();
    private final int value;

    FakePlayerDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FakePlayerDataType toFakePlayerDataType(int type) {
        return fakePlayerDataTypeMap.get(type);
    }

    static {
        FakePlayerDataType[] types = FakePlayerDataType.values();
        for (FakePlayerDataType type : types) {
            fakePlayerDataTypeMap.put(type.getValue(), type);
        }
    }
}