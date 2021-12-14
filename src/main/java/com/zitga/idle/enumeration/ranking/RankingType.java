package com.zitga.idle.enumeration.ranking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum RankingType {
    NO_RANKING(-1),
    IRON_1(1),
    IRON_2(2),
    BRONZE(3),
    SILVER(4),
    GOLD(5),
    PLATINUM(6),
    MASTER(7)
    ;

    private static final Map<Integer, RankingType> rankingTypeMap = new ConcurrentHashMap<>();
    private final int value;

    RankingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RankingType toType(int type) {
        return rankingTypeMap.get(type);
    }

    static {
        RankingType[] rankingTypes = RankingType.values();
        for (RankingType rankingType : rankingTypes) {
            rankingTypeMap.put(rankingType.getValue(), rankingType);
        }
    }
}
