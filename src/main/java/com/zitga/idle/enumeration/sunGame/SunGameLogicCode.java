package com.zitga.idle.enumeration.sunGame;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SunGameLogicCode {

    SUCCESS(1),

    WRONG_SERVER_ID(-1),
    WRONG_CHARACTER_INFO(-2),
    WRONG_SIGN(-4),
    ORDER_ID_ALREADY_PROCESSED(-5),
    WRONG_PRODUCT_ID(-6),

    SYSTEM_ERROR(100),
    ;

    private static final Map<Integer, SunGameLogicCode> codeMap = new ConcurrentHashMap<>();
    private final int value;

    SunGameLogicCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SunGameLogicCode toCode(int code) {
        return codeMap.get(code);
    }

    static {
        SunGameLogicCode[] codes = SunGameLogicCode.values();
        for (SunGameLogicCode code : codes) {
            codeMap.put(code.getValue(), code);
        }
    }
}