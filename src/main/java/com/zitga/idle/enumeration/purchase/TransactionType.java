package com.zitga.idle.enumeration.purchase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TransactionType {
    RAW_PACK(0),
    SUBSCRIPTION_PACK(1),
    EVENT_MID_AUTUMN(2),
    LIMITED_PACK(3),

    EVENT_BUNDLE(4),
    EVENT_HOT_DEAL(5),

    EVENT_ARENA_PASS(6),
    EVENT_DAILY_QUEST_PASS(7),

    GROWTH_PACK(8),

    PROGRESS_PACK(9),

    EVENT_HALLOWEEN_BUNDLE(10),
    EVENT_HALLOWEEN_DAILY_PACK(11),

    EVENT_BLACK_FRIDAY(12),
    EVENT_SALE_OFF(13),

    EVENT_NEW_YEAR_DAILY_BUNDLE(14),
    EVENT_NEW_YEAR_CARD(15),

    EVENT_CHRISTMAS_DAILY_PURCHASE(16),
    EVENT_CHRISTMAS_EXCLUSIVE_OFFER(17),

    EVENT_LUNAR_NEW_YEAR_LUNAR_BUNDLE(18),
    EVENT_LUNAR_NEW_YEAR_ELITE_BUNDLE(19),
    EVENT_LUNAR_NEW_YEAR_SKIN_BUNDLE(20),

    EVENT_VALENTINE_LOVE_CHALLENGE_BUNDLE(21),
    EVENT_VALENTINE_ELITE_BUNDLE(22),
    EVENT_VALENTINE_SKIN_BUNDLE(23),

    EVENT_NEW_HERO_BUNDLE(24),

    EVENT_SERVER_MERGE_BUNDLE(25),
    EVENT_EASTER_BUNNY_CARD(26),
    EVENT_EASTER_LIMIT_OFFER(27),
    EVENT_EASTER_DAILY_BUNDLE(28),

    COMEBACK_BUNDLE(29),

    EVENT_NEW_HERO_SKIN_BUNDLE(30),

    EVENT_SKIN_BUNDLE(31),

    EVENT_BIRTHDAY_DAILY_BUNDLE(32),
    EVENT_BIRTHDAY_ANNIVERSARY_OFFER(33),

    ITEM_SKIN_BUNDLE(34),

    EVENT_NEW_HERO_RELEASE(35),
    CUSTOMIZE_BUNDLE_PACK(36),

    EVENT_BATTLE_PASS_BUNDLE(37),
    EVENT_BATTLE_PASS_LEVEL_BUNDLE(38),

    EVENT_SERVER_OPEN_BUNDLE(39),

    EVENT_BLACK_FRIDAY_BUNDLE(40),

    EVENT_SERVER_OPEN_2_BUNDLE(41),

    EVENT_SERVER_OPEN_3_BUNDLE(42),

    EVENT_IDLE_EFFECT_BUNDLE(43),

    EVENT_IDLE_EFFECT_BUNDLE2(44),

    ;

    private static final Map<Integer, TransactionType> transactionTypeMap = new ConcurrentHashMap<>();

    static {
        TransactionType[] transactionTypes = TransactionType.values();
        for (TransactionType transactionType : transactionTypes) {
            transactionTypeMap.put(transactionType.getValue(), transactionType);
        }
    }

    private final int value;

    TransactionType(int value) {
        this.value = value;
    }

    public static TransactionType toType(int type) {
        return transactionTypeMap.get(type);
    }

    public int getValue() {
        return this.value;
    }
}