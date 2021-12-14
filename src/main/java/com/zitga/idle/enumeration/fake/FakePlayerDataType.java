package com.zitga.idle.enumeration.fake;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum FakePlayerDataType {
    ADD_HERO_FRAGMENT(0),
    ADD_HERO(1),
    ADD_ITEM(2),
    ADD_ARTIFACT(3),
    ADD_ARTIFACT_FRAGMENT(4),
    ADD_SKIN(5),
    ADD_SKIN_FRAGMENT(6),

    SET_MONEY(7),
    REFRESH_MARKET(8),

    DELETE_ITEM(9),
    CLEAR_ALL_ITEMS(10),
    DELETE_HERO(11),
    DELETE_HERO_FRAGMENT(12),
    CLEAR_ALL_HEROES(13),
    RESET_SUMMONER(14),
    SET_VIP(15),
    SET_SUMMONER_INFO(16),

    DECREASE_SUMMON_TIME(18),

    CLEAR_SUBSCRIPTION_PACK(19),

    ADD_AVAILABLE_HERO(20),
    RESET_DUNGEON(21),

    RESET_TOWER(22),

    ADD_CAMPAIGN_QUICK_BATTLE_TICKET(23),
    RESET_CAMPAIGN(24),

    ADD_HERO_FOOD(27),

    ADD_PROGRESS_GROUP(29),
    CLEAR_PROGRESS_GROUP(30),

    RESET_EMAIL_RESEND_TIME(32),

    RESET_TUTORIAL(34),
    SPEED_UP_TIME(35),

    CLEAR_ARENA_PASS(36),
    CLEAR_DAILY_QUEST_PASS(37),

    DEFENSE_MODE_IDLE_STAGE(38),

    RAISED_HERO_CLEAR_DATA(39),

    ARENA_ELO(40),
    ARENA_TEAM_ELO(41),

    GUILD_LEVEL(42),

    OFFLINE_TIME(43),

    DOMAIN_CHALLENGE_DAY(44),

    RESOURCE(45),

    AB_TEST_CONFIG(50),

    //idle effect
    ADD_IDLE_EFFECT(60),

    // event
    EVENT_HALLOWEEN_FAKE_CLAIM_DAY(100),
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