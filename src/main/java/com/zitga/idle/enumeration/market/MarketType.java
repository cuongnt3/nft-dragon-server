package com.zitga.idle.enumeration.market;

public enum MarketType {
    MARKET(0),
    ALTAR_MARKET(1),
    ARENA_TEAM_MARKET(2),
    ARENA_MARKET(3),
    GUILD_MARKET(4),
    EVENT_MARKET(5),
    ;

    private final int value;

    MarketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MarketType toMarketType(int type) {
        return MarketType.values()[type];
    }
}
