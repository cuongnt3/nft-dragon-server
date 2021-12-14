package com.zitga.idle.enumeration.lua;

public enum LuaRunMode {
    TEST(1),
    NORMAL(2),
    FAST(3),
    FASTEST(4)
    ;

    private final int value;

    LuaRunMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static LuaRunMode toLuaRunMode(int mode) {
        return LuaRunMode.values()[mode - 1];
    }
}
