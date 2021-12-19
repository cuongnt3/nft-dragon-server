package com.zitga.idle.enumeration.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum GameMode {
    TEST(0),
    PVE(1),
    ;

    private static final Map<Integer, GameMode> gameModeMap = new ConcurrentHashMap<>();
    private final int value;

    GameMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GameMode toGameMode(int type) {
        return gameModeMap.get(type);
    }

    static {
        GameMode[] gameModes = GameMode.values();
        for (GameMode gameMode : gameModes) {
            gameModeMap.put(gameMode.getValue(), gameMode);
        }
    }
}
