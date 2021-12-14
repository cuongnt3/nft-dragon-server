package com.zitga.idle.enumeration.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum GameMode {
    TEST(0),
    CAMPAIGN(1),
    TOWER(2),
    RAID(3),
    DUNGEON(4),
    FRIEND_BATTLE(5),
    ARENA(6),
    GUILD_SIEGE(7),
    GUILD_WAR(8),
    UNUSED_1(9), // should be replaced by real value
    GUILD_BOSS(10),
    GUILD_DUNGEON(11),
    DEFENSE_MODE(12),
    ARENA_TEAM(13),
    DOMAINS(14),

    // event
    EVENT_CHRISTMAS(20),
    EVENT_LUNAR_NEW_YEAR_GUILD_BOSS(21),
    EVENT_VALENTINE(22),
    EVENT_NEW_HERO_BOSS(23),
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
