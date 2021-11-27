package com.zitga.enumeration.player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum PlayerDataType {
    DRAGON_COLLECTION(0),
    SUMMON(1),
    ;

    private static final Map<Integer, PlayerDataType> playerDataTypeMap = new ConcurrentHashMap<>();
    private final int value;

    PlayerDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static PlayerDataType toPlayerDataType(int type) {
        return playerDataTypeMap.get(type);
    }

    static {
        PlayerDataType[] playerDataTypes = PlayerDataType.values();
        for (PlayerDataType type : playerDataTypes) {
            playerDataTypeMap.put(type.getValue(), type);
        }
    }
}